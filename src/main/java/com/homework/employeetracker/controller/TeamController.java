package com.homework.employeetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.employeetracker.dto.request.CreateTeamRequest;
import com.homework.employeetracker.dto.request.UpdateTeamRequest;
import com.homework.employeetracker.dto.response.TeamResponse;
import com.homework.employeetracker.dto.response.TeamResponseWithMembers;
import com.homework.employeetracker.service.TeamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/teams")
@RestController
@RequiredArgsConstructor
@Tag(name = "Teams API", description = "Endpoints for teams management")
@Validated
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    @Operation(summary = "Find all teams")
    public List<TeamResponse> findAll() {
        return teamService.findAllTeams();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find specific team by ID")
    public TeamResponseWithMembers findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create new team")
    public ResponseEntity<?> create(@Valid @RequestBody CreateTeamRequest request) {
        teamService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update existing teams")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateTeamRequest request) {
        teamService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().build();
    }

}
