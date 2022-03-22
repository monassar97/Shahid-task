package com.shahid.employees.util.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
    public EmployeeAlreadyExistException() {
        super("EMPLOYEE_ALREADY_EXIST_EXCEPTION");
    }
}
