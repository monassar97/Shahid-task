package com.shahid.employees.adapter.repository;

import com.shahid.employees.model.entity.Employee;
import com.shahid.employees.model.enums.ActiveStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByActiveStatus(ActiveStatus activeStatus);
}
