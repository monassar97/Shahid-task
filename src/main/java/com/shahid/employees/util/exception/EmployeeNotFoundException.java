package com.shahid.employees.util.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("EMPLOYEE_NOT_FOUND_EXCEPTION");
    }
}
