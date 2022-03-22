package com.shahid.employees.util.exception;

public class FileDownloadFailedException extends IllegalStateException {
    public FileDownloadFailedException() {
        super("FILE_DOWNLOAD_FAILED_EXCEPTION");
    }
}
