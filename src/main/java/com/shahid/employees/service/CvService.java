package com.shahid.employees.service;

import com.shahid.employees.adapter.repository.CvRepository;
import com.shahid.employees.adapter.repository.EmployeeRepository;
import com.shahid.employees.model.entity.Cv;
import com.shahid.employees.model.enums.BucketName;
import com.shahid.employees.util.exception.*;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class CvService {
    private final FileStore fileStore;
    private final CvRepository repository;
    private final EmployeeRepository employeeRepository;

    public Cv saveCv(String employeeId, String description, MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new UploadEmptyFileException();
        }
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException();
        }
        //Check if the file is pdf or msword
        if (!Arrays.asList(ContentType.create("application/pdf").getMimeType(),
                ContentType.create("application/msword").getMimeType()).contains(file.getContentType())) {
            throw new UploadWrongTypeFileException();
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save cv in S3 and then save Cv in the database
        String path = String.format("%s", BucketName.EMPLOYEE_CV_BUCKET.getBucketName());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(fileName, path, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
        Cv cv = Cv.builder()
                .id(UUID.randomUUID().toString())
                .description(description)
                .employeeId(employeeId)
                .imagePath(path)
                .imageFileName(fileName)
                .build();
        return repository.save(cv);
    }

    public byte[] downloadCv(String employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException();
        }
        if (!repository.findByEmployeeId(employeeId).isPresent()) {
            throw new FileNotFoundException();
        }
        Cv cv = repository.findByEmployeeId(employeeId).get();
        return fileStore.download(cv.getImagePath(), cv.getImageFileName());
    }

    public List<Cv> getAllCvs() {
        return repository.findAll();
    }
}