package com.example.match.repository;

import com.example.match.model.Leaving;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRepository extends CrudRepository<Leaving, Long> {
}
