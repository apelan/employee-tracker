package com.homework.employeetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTeamRequest(
    @NotNull(message = "Team ID cannot be null")
    Long id,
    @NotBlank(message = "Team name cannot be null")
    @Size(max = 100, message = "Max length allowed for team name is 100 characters")
    String name,
    Long teamLeadId) {

}
