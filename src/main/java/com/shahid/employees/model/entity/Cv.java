package com.shahid.employees.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "cv")
public class Cv {
    @Id
    private String id;
    private String title;
    private String description;
    private String imagePath;//add employeeId
    private String imageFileName;

}