package com.example.match.controller;


import com.example.match.exception.NotFoundException;
import com.example.match.model.Organization;
import com.example.match.model.OrganizationCredential;
import com.example.match.model.Owner;
import com.example.match.service.OrganizationCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    OrganizationCredentialService organizationCredentialService;

    @PostMapping("/login")
    public Organization login(@RequestParam("email") String email,
                        @RequestParam("password") String password) throws IOException {
        if(email != null){
            Organization organization = organizationCredentialService.login(email,password);
            if(organization != null){
                return organization;
            }
            return new Organization();
        }
        return new Organization();
    }
    //For dumb method for testing
    @PostMapping("/save")
    public String save(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("org") Integer organizationId) throws IOException {
        if(email != null){
            organizationCredentialService.save(organizationId,email,password);
            Organization organization = organizationCredentialService.login(email,password);
            return organization.getName();
        }
        return "Email is incorrect";
    }
}
