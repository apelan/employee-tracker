package com.homework.employeetracker.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.homework.employeetracker.data.entity.Employee;
import com.homework.employeetracker.data.entity.Team;
import com.homework.employeetracker.data.repository.EmployeeRepository;
import com.homework.employeetracker.data.repository.TeamRepository;
import com.homework.employeetracker.dto.request.CreateEmployeeRequest;
import com.homework.employeetracker.dto.request.SearchEmployeeRequest;
import com.homework.employeetracker.dto.request.UpdateEmployeeRequest;
import com.homework.employeetracker.dto.response.EmployeeResponse;
import com.homework.employeetracker.exception.NotFoundException;
import com.homework.employeetracker.service.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Override
    public void create(CreateEmployeeRequest request) {
        log.debug("Creating employee, request {}", request);

        Employee employee = new Employee();
        employee.setName(request.name());
        employee.setTeam(findTeam(request.teamId()));
        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeRequest request) {
        log.debug("Updating employee, request {}", request);

        Employee employee = employeeRepository.findById(request.id())
            .orElseThrow(() -> new NotFoundException(Employee.class));

        employee.setName(request.name());
        employee.setTeam(findTeam(request.teamId()));
        employeeRepository.save(employee);

    }

    @Override
    public void delete(Long employeeId) {
        log.debug("Deleting employee {}", employeeId);
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeResponse findById(Long employeeId) {
        log.debug("Finding employee by ID {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new NotFoundException(Employee.class));

        return new EmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> searchEmployees(SearchEmployeeRequest request, Integer pageNumber, Integer pageSize) {
        log.debug("Search employees, request {}, pageNumber {}, pageSize {}", request, pageNumber, pageSize);
        Page<Employee> employees = employeeRepository.searchEmployees(request.name(),
            request.teamName(),
            request.teamLead(),
            PageRequest.of(pageNumber, pageSize, Sort.by("id")));

        return employees.stream().map(EmployeeResponse::new).toList();
    }

    private Team findTeam(Long teamId) {
        if (teamId == null) return null;
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new NotFoundException(Team.class));
    }

}
