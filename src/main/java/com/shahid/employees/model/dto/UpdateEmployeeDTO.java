package com.shahid.employees.model.dto;

import com.shahid.employees.model.enums.ActiveStatus;
import com.shahid.employees.model.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeDTO {
    private String name;
    private double salary;
    private Department department;
    private ActiveStatus activeStatus;
    private String manager;
}
