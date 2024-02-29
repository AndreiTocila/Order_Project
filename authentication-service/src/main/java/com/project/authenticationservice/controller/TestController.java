package com.project.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController
{

    @GetMapping("/client")
    @PreAuthorize("hasAnyAuthority('client')")
    public String test()
    {
        return "Merge client";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String testAdmin()
    {
        return "Merge admin";
    }
}
