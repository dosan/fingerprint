package com.example.match.repository;

import com.example.match.model.Organization;
import com.example.match.model.OrganizationCredential;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationCredentialRepository extends CrudRepository<OrganizationCredential,Integer> {
    OrganizationCredential findByEmail(String email);
    //OrganizationCredential get
}
