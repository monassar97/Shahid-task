package com.shahid.employees.model.entity;

import com.shahid.employees.model.enums.ActiveStatus;
import com.shahid.employees.model.enums.Department;
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
@Document(collection = "employees")
public class Employee {
    @Id
    private String employeeId;
    private String name;
    private Double salary;
    private Department department;
    private ActiveStatus activeStatus;
    private String manager;
}
