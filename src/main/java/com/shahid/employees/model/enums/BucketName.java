package com.shahid.employees.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    EMPLOYEE_CV_BUCKET("shahid.employee.buckets");
    private final String bucketName;
}