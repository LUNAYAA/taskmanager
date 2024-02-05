package com.luna.taskmanager.validations;

import com.luna.taskmanager.exception.AppException;

public interface Validator {
    public <T> void validate(T object) throws AppException;
}
