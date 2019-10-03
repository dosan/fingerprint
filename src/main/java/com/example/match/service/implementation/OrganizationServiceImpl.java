package com.example.match.service.implementation;

import com.example.match.model.Organization;
import com.example.match.repository.OrganizationRepository;
import com.example.match.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Organization getOrganizationById(Integer id) {
        return organizationRepository.getOrganizationById(id);
    }
}
