package com.homework.employeetracker.service;

import java.util.List;

import com.homework.employeetracker.dto.request.CreateEmployeeRequest;
import com.homework.employeetracker.dto.request.SearchEmployeeRequest;
import com.homework.employeetracker.dto.request.UpdateEmployeeRequest;
import com.homework.employeetracker.dto.response.EmployeeResponse;

public interface EmployeeService {

    void create(CreateEmployeeRequest request);
    void update(UpdateEmployeeRequest request);
    void delete(Long employeeId);
    EmployeeResponse findById(Long employeeId);
    List<EmployeeResponse> searchEmployees(SearchEmployeeRequest request, Long pageNumber, Long pageSize);

}
