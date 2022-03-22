package com.shahid.employees.service;

import com.shahid.employees.adapter.repository.EmployeeRepository;
import com.shahid.employees.model.common.EmployeeSearchFilter;
import com.shahid.employees.model.dto.AddEmployeeDTO;
import com.shahid.employees.model.dto.RaiseSalaryDTO;
import com.shahid.employees.model.dto.UpdateEmployeeDTO;
import com.shahid.employees.model.entity.Employee;
import com.shahid.employees.model.enums.ActiveStatus;
import com.shahid.employees.util.exception.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MongoOperations mongoOperations;

    public EmployeeService(EmployeeRepository employeeRepository, MongoOperations mongoOperations) {
        this.employeeRepository = employeeRepository;
        this.mongoOperations = mongoOperations;
    }

    public Employee addEmployee(AddEmployeeDTO employeeDTO) {
        return employeeRepository.save(Employee.builder()
                .employeeId(UUID.randomUUID().toString())
                .name(employeeDTO.getName())
                .manager(employeeDTO.getManager())
                .department(employeeDTO.getDepartment())
                .activeStatus(employeeDTO.getActiveStatus())
                .salary(employeeDTO.getSalary())
                .build());
    }

    public Employee updateEmployee(String employeeId, UpdateEmployeeDTO updateEmployeeDTO) {
        if (!employeeRepository.findById(employeeId).isPresent()) {
            throw new EmployeeNotFoundException();
        }
        return employeeRepository.save(Employee.builder()
                .employeeId(employeeId)
                .activeStatus(updateEmployeeDTO.getActiveStatus())
                .department(updateEmployeeDTO.getDepartment())
                .manager(updateEmployeeDTO.getManager())
                .name(updateEmployeeDTO.getName())
                .salary(updateEmployeeDTO.getSalary())
                .build());
    }


    public void deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getEmployeesByActiveStatus(ActiveStatus activeStatus) {
        return employeeRepository.findByActiveStatus(activeStatus);
    }

    public List<Employee> search(EmployeeSearchFilter searchFilter) {
        Query mongoQuery = new Query();

        if (!ObjectUtils.isEmpty(searchFilter.getName())) {
            mongoQuery.addCriteria(Criteria.where("name").is(searchFilter.getDepartment()));
        }
        if (!ObjectUtils.isEmpty(searchFilter.getDepartment())) {
            mongoQuery.addCriteria(Criteria.where("department").in(searchFilter.getDepartment()));
        }

        return mongoOperations.find(mongoQuery, Employee.class);
    }

    public Page<Employee> getTopPaid(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "salary"));

        return employeeRepository.findAll(paging);
    }

    public Employee updateSalary(String employeeId, RaiseSalaryDTO raiseSalaryDTO) {
        if (!employeeRepository.findById(employeeId).isPresent()) {
            throw new EmployeeNotFoundException();
        }
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setSalary(employee.getSalary() + employee.getSalary() * raiseSalaryDTO.getPercent().doubleValue());
        return employeeRepository.save(employee);
    }
}
