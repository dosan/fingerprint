package com.example.match.service;

import com.example.match.model.Organization;
import com.example.match.model.OrganizationCredential;

public interface OrganizationCredentialService {
    OrganizationCredential findByEmail(String email);
    Organization login(String email, String password);
    void save(Integer organizationId,String email,String password);
}
