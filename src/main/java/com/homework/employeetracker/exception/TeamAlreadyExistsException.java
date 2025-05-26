package com.homework.employeetracker.exception;

public class TeamAlreadyExistsException extends RuntimeException {

    public TeamAlreadyExistsException() {
        super("Team with that name already exists.");
    }
}
