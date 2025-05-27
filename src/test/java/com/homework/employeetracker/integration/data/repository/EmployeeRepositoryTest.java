package com.homework.employeetracker.integration.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.homework.employeetracker.data.entity.Employee;
import com.homework.employeetracker.data.repository.EmployeeRepository;

@ActiveProfiles("integ-test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void givenEmptyRequest_whenSearchEmployees_thenReturnAll() {
        Page<Employee> result = employeeRepository.searchEmployees(
            null, null, null, PageRequest.of(0, 10)
        );

        assertEquals(4, result.getContent().size());
    }

    @Test
    void givenPaginatedRequest_whenSearchEmployees_thenReturnPaginatedResponse() {
        Page<Employee> result = employeeRepository.searchEmployees(
            null, null, null, PageRequest.of(0, 2)
        );

        assertEquals(2, result.getContent().size());
    }

    @Test
    void givenName_whenSearchEmployees_thenAssertFoundCorrectly() {
        Page<Employee> result = employeeRepository.searchEmployees(
            "Mirko", null, null, PageRequest.of(0, 10)
        );

        assertEquals(1, result.getContent().size());
        assertEquals("Mirko", result.getContent().getFirst().getName());
    }

    @Test
    void givenTeamName_whenSearchEmployees_thenAssertFoundCorrectly() {
        Page<Employee> result = employeeRepository.searchEmployees(
            null, "Development", null, PageRequest.of(0, 10)
        );

        assertEquals(4, result.getContent().size());
    }

    @Test
    void givenTeamLead_whenSearchEmployees_thenAssertFoundCorrectly() {
        Page<Employee> result = employeeRepository.searchEmployees(
            null, null, "Mirko", PageRequest.of(0, 10)
        );

        assertEquals(4, result.getContent().size());
    }


    @Test
    void givenAllFilters_whenSearchEmployees_thenAssertFoundCorrectly() {
        Page<Employee> result = employeeRepository.searchEmployees(
            "Predrag", "Development", "Mirko", PageRequest.of(0, 10)
        );

        assertEquals(1, result.getContent().size());
        assertEquals("Predrag", result.getContent().getFirst().getName());
    }

}
