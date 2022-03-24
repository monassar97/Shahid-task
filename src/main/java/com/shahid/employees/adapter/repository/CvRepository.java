package com.shahid.employees.adapter.repository;

import com.shahid.employees.model.entity.Cv;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CvRepository extends MongoRepository<Cv, String> {
    Optional<Cv> findByEmployeeId(String employeeId);
}
