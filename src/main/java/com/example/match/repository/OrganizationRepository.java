package com.example.match.repository;

import com.example.match.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization,Integer> {
    Organization getOrganizationById(Integer id);
}
