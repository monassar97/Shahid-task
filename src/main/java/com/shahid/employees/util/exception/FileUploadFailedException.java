package com.shahid.employees.util.exception;

public class FileUploadFailedException extends IllegalStateException {
    public FileUploadFailedException() {
        super("FILE_UPLOAD_FAILED_EXCEPTION");
    }
}
