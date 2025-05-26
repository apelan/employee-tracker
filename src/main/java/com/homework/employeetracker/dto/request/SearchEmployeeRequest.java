package com.homework.employeetracker.dto.request;

import jakarta.validation.constraints.Size;

public record SearchEmployeeRequest(
    @Size(max = 100, message = "Max length allowed for employee name is 100 characters")
    String name,
    @Size(max = 100, message = "Max length allowed for team name is 100 characters")
    String teamName,
    @Size(max = 100, message = "Max length allowed for team lead is 100 characters")
    String teamLead
) {

}
