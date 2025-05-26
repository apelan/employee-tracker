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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homework.employeetracker.dto.request.CreateEmployeeRequest;
import com.homework.employeetracker.dto.request.SearchEmployeeRequest;
import com.homework.employeetracker.dto.request.UpdateEmployeeRequest;
import com.homework.employeetracker.dto.response.EmployeeResponse;
import com.homework.employeetracker.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/employees")
@RestController
@RequiredArgsConstructor
@Tag(name = "Employees API", description = "Endpoints for employees management")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/search")
    @Operation(summary = "Search employees")
    public List<EmployeeResponse> searchEmployees(@Valid @RequestBody SearchEmployeeRequest request,
        @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        return employeeService.searchEmployees(request, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find specific employee by ID")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create new employee")
    public ResponseEntity<?> create(@Valid @RequestBody CreateEmployeeRequest request) {
        employeeService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update existing employee")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateEmployeeRequest request) {
        employeeService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
