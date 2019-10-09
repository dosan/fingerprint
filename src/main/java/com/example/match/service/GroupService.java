package com.example.match.service;

import com.example.match.model.TempClass;

import java.util.ArrayList;

public interface GroupService {
    void save(int organizationId, int ownerId);
    void delete(int ownerId);
    Iterable<TempClass> findOwnersByOrganization(int organizationId);
}
