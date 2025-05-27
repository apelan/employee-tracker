package com.homework.employeetracker.exception;

import java.sql.Timestamp;
import java.util.List;

public record GenericErrorResponse(
    Timestamp timestamp,
    int status,
    List<String> messages
) {

}
