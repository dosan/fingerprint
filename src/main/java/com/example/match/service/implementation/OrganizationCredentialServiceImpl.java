package com.example.match.service.implementation;

import com.example.match.model.Organization;
import com.example.match.model.OrganizationCredential;
import com.example.match.repository.OrganizationCredentialRepository;
import com.example.match.repository.OrganizationRepository;
import com.example.match.service.OrganizationCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrganizationCredentialServiceImpl implements OrganizationCredentialService {

    @Autowired
    OrganizationCredentialRepository organizationCredentialRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public OrganizationCredential findByEmail(String email) {
        return organizationCredentialRepository.findByEmail(email);
    }

    @Override
    public Organization login(String email, String password) {
        System.out.println(email);
        System.out.println(password);

        OrganizationCredential organizationCredential = findByEmail(email);
        if(organizationCredential == null){
            return null;
        }
        if (BCrypt.checkpw(password, organizationCredential.getPassword())) {
            System.out.println("It matches");
            return organizationCredential.getOrganization();
        }else{
            System.out.println("It does not match");
            return null;
        }
    }

    @Override
    public void save(Integer organizationId, String email, String password) {
            Organization organization = organizationRepository.getOrganizationById(organizationId);
            String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
            Date date = new Date(System.currentTimeMillis());

            OrganizationCredential organizationCredential = new OrganizationCredential();
            organizationCredential.setEmail(email);
            organizationCredential.setOrganization(organization);
            organizationCredential.setPassword(pw_hash);
            organizationCredential.setCreatedAt(date);
            organizationCredential.setCreatedAt(date);
            organizationCredentialRepository.save(organizationCredential);
    }
}
