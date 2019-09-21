package com.example.match.repository;

import com.example.match.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<Owner, Integer> {
    Owner findByNameAndSurnameAndIin(String name, String surname, String iin);
}
