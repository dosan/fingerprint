package com.example.match.repository;

import com.example.match.model.AccessDay;
import org.springframework.data.repository.CrudRepository;

public interface AccessDayRepository extends CrudRepository<AccessDay,Integer> {
    AccessDay getFirstByDayInteger(Integer currentDay);
}
