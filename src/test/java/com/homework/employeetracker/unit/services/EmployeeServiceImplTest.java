package com.homework.employeetracker.unit.services;

import static com.homework.employeetracker.unit.TestMockData.EMPLOYEE_ID;
import static com.homework.employeetracker.unit.TestMockData.EMPLOYEE_NAME;
import static com.homework.employeetracker.unit.TestMockData.TEAM_ID;
import static com.homework.employeetracker.unit.TestMockData.TEAM_NAME;
import static com.homework.employeetracker.unit.TestMockData.mockEmployee;
import static com.homework.employeetracker.unit.TestMockData.mockTeam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.homework.employeetracker.data.entity.Employee;
import com.homework.employeetracker.data.repository.EmployeeRepository;
import com.homework.employeetracker.data.repository.TeamRepository;
import com.homework.employeetracker.dto.request.CreateEmployeeRequest;
import com.homework.employeetracker.dto.request.SearchEmployeeRequest;
import com.homework.employeetracker.dto.request.UpdateEmployeeRequest;
import com.homework.employeetracker.dto.response.EmployeeResponse;
import com.homework.employeetracker.exception.NotFoundException;
import com.homework.employeetracker.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void givenValidRequest_whenCreate_thenAssertEmployeeCreated() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(EMPLOYEE_NAME, TEAM_ID);
        when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.of(mockTeam()));

        employeeService.create(request);

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void givenRequestWithoutTeam_whenCreate_thenAssertEmployeeCreated() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(EMPLOYEE_NAME, null);

        employeeService.create(request);

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void givenRequest_whenUpdate_thenAssertUpdated() {
        UpdateEmployeeRequest request = new UpdateEmployeeRequest(EMPLOYEE_ID, EMPLOYEE_NAME, TEAM_ID);
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(mockEmployee()));
        when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.of(mockTeam()));

        employeeService.update(request);

        verify(employeeRepository).save(any(Employee.class));

    }

    @Test
    void givenId_whenDelete_thenAssertDeleted() {
        employeeService.delete(EMPLOYEE_ID);

        verify(employeeRepository).deleteById(EMPLOYEE_ID);
    }

    @Test
    void givenId_whenFindById_thenAssertFound() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(mockEmployee()));

        EmployeeResponse response = employeeService.findById(EMPLOYEE_ID);

        assertNotNull(response);
        assertEquals(EMPLOYEE_NAME, response.name());
    }

    @Test
    void givenId_whenFindById_thenAssertNotFound() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> employeeService.findById(EMPLOYEE_ID));
    }

    @Test
    void givenRequest_whenSearchEmployees_thenAssertEmployeesFound() {
        SearchEmployeeRequest request = new SearchEmployeeRequest(EMPLOYEE_NAME, TEAM_NAME, EMPLOYEE_NAME);
        when(employeeRepository.searchEmployees(request.name(),
            request.teamName(),
            request.teamLead(),
            PageRequest.of(0, 10, Sort.by("id"))))
            .thenReturn(new PageImpl<>(List.of(mockEmployee())));

        List<EmployeeResponse> response = employeeService.searchEmployees(request, 0, 10);

        assertFalse(response.isEmpty());
        assertEquals(EMPLOYEE_NAME, response.getFirst().name());
    }

}
