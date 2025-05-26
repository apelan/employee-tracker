package com.homework.employeetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTeamRequest(
    @NotBlank(message = "Team name cannot be null")
    @Size(max = 100, message = "Max length allowed for team name is 100 characters")
    String name,
    Long teamLeadId) {

}
