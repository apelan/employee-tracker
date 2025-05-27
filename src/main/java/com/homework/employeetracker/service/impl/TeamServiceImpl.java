package com.homework.employeetracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homework.employeetracker.data.entity.Employee;
import com.homework.employeetracker.data.entity.Team;
import com.homework.employeetracker.data.repository.EmployeeRepository;
import com.homework.employeetracker.data.repository.TeamRepository;
import com.homework.employeetracker.dto.request.CreateTeamRequest;
import com.homework.employeetracker.dto.request.UpdateTeamRequest;
import com.homework.employeetracker.dto.response.TeamResponse;
import com.homework.employeetracker.dto.response.TeamResponseWithMembers;
import com.homework.employeetracker.exception.NotFoundException;
import com.homework.employeetracker.exception.TeamAlreadyExistsException;
import com.homework.employeetracker.service.TeamService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void create(CreateTeamRequest request) {
        log.debug("Creating team, request {}", request);

        if (teamRepository.existsByName(request.name())) {
            throw new TeamAlreadyExistsException();
        }

        Employee teamLead = findTeamLead(request.teamLeadId());

        Team team = new Team();
        team.setName(request.name());
        team.setLead(teamLead);
        teamRepository.save(team);

        teamLead.setTeam(team);
        employeeRepository.save(teamLead);
    }

    @Override
    public void update(UpdateTeamRequest request) {
        log.debug("Updating team, request {}", request);

        Employee teamLead = findTeamLead(request.teamLeadId());

        Team team = teamRepository.findById(request.id())
            .orElseThrow(() -> new NotFoundException(Team.class));
        team.setName(request.name());
        team.setLead(teamLead);
        teamRepository.save(team);

        teamLead.setTeam(team);
        employeeRepository.save(teamLead);
    }

    @Override
    public void delete(Long teamId) {
        log.debug("Deleting team with ID {}", teamId);
        teamRepository.deleteById(teamId);
    }

    @Override
    public TeamResponseWithMembers findById(Long teamId) {
        log.debug("Finding team by ID {}", teamId);
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new NotFoundException(Team.class));

        return new TeamResponseWithMembers(team);
    }

    @Override
    public List<TeamResponse> findAllTeams() {
        log.debug("Finding all teams..");
        List<Team> teams = teamRepository.findAll(); // <--- not good practice -  if high number of teams, add pagination
        return teams.stream().map(TeamResponse::new).toList();
    }

    private Employee findTeamLead(Long teamLeadId) {
        if (teamLeadId == null) return null;
        return employeeRepository.findById(teamLeadId)
            .orElseThrow(() -> new NotFoundException(Employee.class));
    }

}
