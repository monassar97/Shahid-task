package com.shahid.employees.adapter.rest;

import com.shahid.employees.model.entity.Cv;
import com.shahid.employees.service.CvService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/cv")
@AllArgsConstructor
@Slf4j
public class CvController {
    CvService service;

    @GetMapping
    public ResponseEntity<List<Cv>> getCvs() {
        return new ResponseEntity<List<Cv>>(service.getAllCvs(), HttpStatus.OK);
    }

    @PostMapping(
            path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Cv> saveCv(@RequestParam("employee id") String employeeId,
                                     @RequestParam("description") String description,
                                     @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<Cv>(service.saveCv(employeeId, description, file), HttpStatus.OK);
    }

    @GetMapping(value = "{employee-id}")
    public byte[] downloadCv(@PathVariable("employee-id") String employeeId) {
        return service.downloadCv(employeeId);
    }
}