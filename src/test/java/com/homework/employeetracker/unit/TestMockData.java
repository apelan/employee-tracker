package com.homework.employeetracker.unit;

import java.util.Collections;

import com.homework.employeetracker.data.entity.Employee;
import com.homework.employeetracker.data.entity.Team;

public final class TestMockData {

    public static final String TEAM_NAME = "TEST_TEAM";
    public static final Long TEAM_ID = 1L;
    public static final String EMPLOYEE_NAME = "TEST_EMPLOYEE_NAME";
    public static final Long EMPLOYEE_ID = 1L;


    public static Team mockTeam() {
        Team team = new Team();
        team.setName(TEAM_NAME);
        team.setMembers(Collections.emptyList());
        return team;
    }

    public static Employee mockEmployee() {
        Employee employee = new Employee();
        employee.setName(EMPLOYEE_NAME);
        employee.setTeam(mockTeam());
        return employee;
    }

}
