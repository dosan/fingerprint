package com.example.match.repository;

import com.example.match.model.Fingerprint;
import com.example.match.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface FingerprintRepository extends CrudRepository<Fingerprint, Long> {
    long countByOwner(Owner owner);
}
