package com.example.match.repository;

import com.example.match.model.TempClass;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<TempClass,Integer> {
    TempClass findByOwe(int ownerId);
    Iterable<TempClass> findAllByOrg(int organizationId);
}
