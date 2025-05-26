package com.homework.employeetracker.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz) {
        super(String.format("Instance <%s> not found", clazz.getSimpleName()));
    }

}
