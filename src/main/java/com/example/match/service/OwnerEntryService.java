package com.example.match.service;

import com.example.match.model.Owner;

public interface OwnerEntryService {
    void register(Owner owner, Integer organizationId);
}
