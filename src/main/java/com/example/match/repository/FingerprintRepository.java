package com.example.match.repository;

import com.example.match.model.Fingerprint;
import com.example.match.model.User;
import org.springframework.data.repository.CrudRepository;

public interface FingerprintRepository extends CrudRepository<Fingerprint, Long> {
    long countByUser(User user);
}
