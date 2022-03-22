package com.shahid.employees.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RaiseSalaryDTO {
    @Range(min = 0, max = 1, message = "raise percentage could only be from 0.0 to 1.0")
    private BigDecimal percent;
}
