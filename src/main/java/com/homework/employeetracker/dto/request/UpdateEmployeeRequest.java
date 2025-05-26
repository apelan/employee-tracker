package com.homework.employeetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEmployeeRequest(
    @NotNull(message = "Employee ID cannot be null")
    Long id,
    @NotBlank(message = "Missing employee name")
    @Size(max = 100, message = "Max length allowed for employee name is 100 characters")
    String name,
    Long teamId) {
}
