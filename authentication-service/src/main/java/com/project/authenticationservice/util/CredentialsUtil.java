package com.project.authenticationservice.util;

import org.keycloak.representations.idm.CredentialRepresentation;

public class CredentialsUtil
{

    public static CredentialRepresentation createPasswordCredentials(String password)
    {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
