package com.homework.employeetracker.dto.response;

import java.util.List;

import com.homework.employeetracker.data.entity.Employee;
import com.homework.employeetracker.data.entity.Team;

public record TeamResponseWithMembers(String name, String teamLead, List<String> members) {

    public TeamResponseWithMembers(Team team) {
        this(team.getName(),
            team.getLead() != null ? team.getLead().getName() : null,
            team.getMembers().stream().map(Employee::getName).toList());
    }

}
