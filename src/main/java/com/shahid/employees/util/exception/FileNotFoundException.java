package com.shahid.employees.util.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super("FILE_NOT_FOUND_EXCEPTION");
    }
}
