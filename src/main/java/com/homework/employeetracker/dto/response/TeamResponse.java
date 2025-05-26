package com.homework.employeetracker.dto.response;

import com.homework.employeetracker.data.entity.Team;

public record TeamResponse(String name, String teamLead) {

    public TeamResponse(Team team) {
        this(team.getName(),
            team.getLead() != null ? team.getLead().getName() : null);
    }

}
