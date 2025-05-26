package com.homework.employeetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEmployeeRequest(
    @NotBlank(message = "Employee name cannot be null")
    @Size(max = 100, message = "Max length allowed for employee name is 100 characters")
    String name,
    Long teamId) {
}
