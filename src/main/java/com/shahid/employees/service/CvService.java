package com.shahid.employees.service;

import com.shahid.employees.adapter.repository.CvRepository;
import com.shahid.employees.model.entity.Cv;
import com.shahid.employees.util.exception.FileNotFoundException;
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

    public Cv saveCv(String title, String description, MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList(ContentType.create("application/pdf").getMimeType(),
                ContentType.create("application/msword").getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Cv in the database
        String path = String.format("%s%s", "https://shahid.employee.buckets.s3.us-east-1.amazonaws.com/", UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        Cv cv = Cv.builder()
                .description(description)
                .title(title)
                .imagePath(path)
                .imageFileName(fileName)
                .build();
        repository.save(cv);
        return repository.findByTitle(cv.getTitle());
    }

    public byte[] downloadCvImage(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new FileNotFoundException();
        }
        Cv cv = repository.findById(id).get();
        return fileStore.download(cv.getImagePath(), cv.getImageFileName());
    }

    public List<Cv> getAllCvs() {
        List<Cv> cvs = new ArrayList<>();
        repository.findAll().forEach(cvs::add);
        return cvs;
    }
}