package com.project.authenticationservice.service.impl;

import com.project.authenticationservice.dto.UserLoginDTO;
import com.project.authenticationservice.dto.UserRegisterDTO;
import com.project.authenticationservice.service.KeycloakService;
import com.project.authenticationservice.util.CredentialsUtil;
import com.project.authenticationservice.util.KeycloakBuilderProvider;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService
{
    private final UsersResource usersResource;

    private final RealmResource realmResource;

    private final KeycloakBuilderProvider keycloakBuilderProvider;

    @Override
    public void createUser(UserRegisterDTO userDTO)
    {
        CredentialRepresentation credential = CredentialsUtil
                .createPasswordCredentials(userDTO.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);

        UserResource userResource = usersResource.get(userId);

        ClientRepresentation client =
                realmResource
                        .clients()
                        .findByClientId("authentication-service").get(0);

        RoleRepresentation userClientRole =
                realmResource
                        .clients()
                        .get(client.getId())
                        .roles()
                        .get("client")
                        .toRepresentation();

        userResource.roles().clientLevel(client.getId()).add(Collections.singletonList(userClientRole));

        client = realmResource
                .clients()
                .findByClientId("order-service").get(0);

        userClientRole =
                realmResource
                        .clients()
                        .get(client.getId())
                        .roles()
                        .get("order_client")
                        .toRepresentation();

        userResource.roles().clientLevel(client.getId()).add(Collections.singletonList(userClientRole));
    }

    public String generateToken(UserLoginDTO userDTO)
    {
        Keycloak keycloak = keycloakBuilderProvider.newKeycloakBuilderWithPasswordCredentials(userDTO.getUsername(), userDTO.getPassword()).build();

        return keycloak.tokenManager().getAccessToken().getToken();
    }
}
