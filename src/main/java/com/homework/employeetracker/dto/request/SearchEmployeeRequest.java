package com.homework.employeetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SearchEmployeeRequest(
    @NotBlank(message = "Employee name cannot be null")
    @Size(max = 100, message = "Max length allowed for employee name is 100 characters")
    String name,
    @NotBlank(message = "Team name cannot be null")
    @Size(max = 100, message = "Max length allowed for team name is 100 characters")
    String teamName,
    @NotBlank(message = "Team lead cannot be null")
    @Size(max = 100, message = "Max length allowed for team lead is 100 characters")
    String teamLead
) {

}
