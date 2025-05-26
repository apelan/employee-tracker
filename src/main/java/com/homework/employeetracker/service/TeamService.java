package com.homework.employeetracker.service;

import java.util.List;

import com.homework.employeetracker.dto.request.CreateTeamRequest;
import com.homework.employeetracker.dto.request.UpdateTeamRequest;
import com.homework.employeetracker.dto.response.TeamResponse;
import com.homework.employeetracker.dto.response.TeamResponseWithMembers;

public interface TeamService {

    void create(CreateTeamRequest request);
    void update(UpdateTeamRequest request);
    void delete(Long teamId);
    TeamResponseWithMembers findById(Long teamId);
    List<TeamResponse> findAllTeams();

}
