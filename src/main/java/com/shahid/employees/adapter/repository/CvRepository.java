package com.shahid.employees.adapter.repository;

import com.shahid.employees.model.entity.Cv;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CvRepository extends MongoRepository<Cv, Long> {
    Cv findByTitle(String title);
}
