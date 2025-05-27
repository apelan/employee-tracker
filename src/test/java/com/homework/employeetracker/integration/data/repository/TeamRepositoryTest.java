package com.homework.employeetracker.integration.data.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.homework.employeetracker.data.repository.TeamRepository;

@ActiveProfiles("integ-test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Test
    void givenName_whenExistsByName_thenAssertExists() {
        assertTrue(teamRepository.existsByName("Development"));
    }

    @Test
    void givenName_whenExistsByName_thenAssertNotExist() {
        assertFalse(teamRepository.existsByName("Not Existing Team Name"));
    }

}
