package com.homework.employeetracker.unit.services;

import static com.homework.employeetracker.unit.TestMockData.EMPLOYEE_ID;
import static com.homework.employeetracker.unit.TestMockData.TEAM_ID;
import static com.homework.employeetracker.unit.TestMockData.TEAM_NAME;
import static com.homework.employeetracker.unit.TestMockData.mockEmployee;
import static com.homework.employeetracker.unit.TestMockData.mockTeam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.homework.employeetracker.service.impl.TeamServiceImpl;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void givenValidRequest_whenCreate_thenAssertTeamCreated() {
        CreateTeamRequest request = new CreateTeamRequest(TEAM_NAME, EMPLOYEE_ID);
        when(teamRepository.existsByName(TEAM_NAME)).thenReturn(false);
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(mockEmployee()));

        teamService.create(request);

        verify(teamRepository).save(any(Team.class));
    }

    @Test
    void givenRequestExistingTeamName_whenCreate_thenAssertTeamCreated() {
        CreateTeamRequest request = new CreateTeamRequest(TEAM_NAME, EMPLOYEE_ID);
        when(teamRepository.existsByName(TEAM_NAME)).thenReturn(true);

        assertThrows(TeamAlreadyExistsException.class, () -> teamService.create(request));

        verify(teamRepository, times(0)).save(any(Team.class));
    }

    @Test
    void givenRequestNotExistingTeamLead_whenCreate_thenAssertTeamCreated() {
        CreateTeamRequest request = new CreateTeamRequest(TEAM_NAME, EMPLOYEE_ID);
        when(teamRepository.existsByName(TEAM_NAME)).thenReturn(false);
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> teamService.create(request));

        verify(teamRepository, times(0)).save(any(Team.class));
    }


    @Test
    void givenRequest_whenUpdate_thenAssertTeamUpdated() {
        UpdateTeamRequest request = new UpdateTeamRequest(TEAM_ID, TEAM_NAME, null);
        when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.of(mockTeam()));

        teamService.update(request);

        verify(teamRepository).save(any(Team.class));
    }

    @Test
    void givenId_whenDelete_thenAssertDeleted() {
        teamService.delete(TEAM_ID);

        verify(teamRepository).deleteById(TEAM_ID);
    }

    @Test
    void givenId_whenFindById_thenAssertFound() {
        when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.of(mockTeam()));

        TeamResponseWithMembers response = teamService.findById(TEAM_ID);

        assertNotNull(response);
        assertEquals(TEAM_NAME, response.name());
    }

    @Test
    void givenId_whenFindById_thenAssertNotFound() {
        when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> teamService.findById(TEAM_ID));
    }

    @Test
    void whenFindAllTeams_thenAssertFound() {
        when(teamRepository.findAll()).thenReturn(Collections.singletonList(mockTeam()));

        List<TeamResponse> response = teamService.findAllTeams();

        assertFalse(response.isEmpty());
        assertEquals(TEAM_NAME, response.getFirst().name());
    }

}
