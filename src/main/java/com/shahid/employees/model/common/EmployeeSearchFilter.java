package com.shahid.employees.model.common;

import com.shahid.employees.model.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSearchFilter {
    private String name;
    private List<Department> department;
}
