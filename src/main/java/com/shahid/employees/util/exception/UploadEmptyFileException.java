package com.shahid.employees.util.exception;

public class UploadEmptyFileException extends IllegalStateException {
    public UploadEmptyFileException() {
        super("UPLOAD_EMPTY_FILE_EXCEPTION");
    }
}
