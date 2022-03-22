package com.shahid.employees.adapter.rest;

import com.shahid.employees.model.common.EmployeeSearchFilter;
import com.shahid.employees.model.dto.RaiseSalaryDTO;
import com.shahid.employees.model.dto.UpdateEmployeeDTO;
import com.shahid.employees.model.entity.Employee;
import com.shahid.employees.model.enums.ActiveStatus;
import com.shahid.employees.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{employee-id}")
    public Employee updateEmployee(@PathVariable("employee-id") String employeeId, @RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        return employeeService.updateEmployee(employeeId, updateEmployeeDTO);
    }

    @DeleteMapping("/{employee-id}")
    public void deleteEmployeeById(@PathVariable(value = "employee-id") String employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping("/active")
    public List<Employee> listActiveEmployees() {
        return employeeService.getEmployeesByActiveStatus(ActiveStatus.ACTIVE);
    }

    @GetMapping("/in-active")
    public List<Employee> listInActiveEmployees() {
        return employeeService.getEmployeesByActiveStatus(ActiveStatus.IN_ACTIVE);
    }

    @GetMapping
    public List<Employee> search(EmployeeSearchFilter searchFilter) {
        return employeeService.search(searchFilter);
    }

    @GetMapping("/top-paid")
    public Page<Employee> findTopPaidEmployees(@RequestParam int pageNo, @RequestParam int pageSize) {
        return employeeService.getTopPaid(pageNo, pageSize);
    }

    @PutMapping("/{employee-id}/raise")
    public Employee raiseSalary(@PathVariable("employee-id") String employeeId, @Validated @RequestBody RaiseSalaryDTO raiseSalaryDTO) {
        return employeeService.updateSalary(employeeId, raiseSalaryDTO);
    }
}
