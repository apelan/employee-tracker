package com.homework.employeetracker.dto.response;

import com.homework.employeetracker.data.entity.Employee;

public record EmployeeResponse(Long id, String name, String teamName, String teamLead) {

    public EmployeeResponse(Employee employee) {
        this(employee.getId(),
            employee.getName(),
            employee.getTeam() != null
                ? employee.getTeam().getName() : null,
            (employee.getTeam() != null && employee.getTeam().getLead() != null)
                ? employee.getTeam().getLead().getName() : null);
    }
}
