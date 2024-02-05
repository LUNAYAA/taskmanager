package com.luna.taskmanager.validations.tasklists;

import com.luna.taskmanager.exception.AppErrors;
import com.luna.taskmanager.exception.AppException;
import com.luna.taskmanager.model.request.CreateTaskListRequest;
import com.luna.taskmanager.validations.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;


import static com.luna.taskmanager.constants.APIConstants.*;
import static com.luna.taskmanager.exception.ErrorMessages.*;

@Component
public class CreateTaskListsValidator implements Validator {

    private static final Logger log = (Logger) LoggerFactory.getLogger(CreateTaskListsValidator.class);

    @Override
    public <T> void validate(T object) throws AppException {
        CreateTaskListRequest createTaskListRequest = (CreateTaskListRequest) object;
        validateMandatory(createTaskListRequest.getName());
        validateLengths(createTaskListRequest);
        log.info("Received request object for task list creation has been validated.");
    }

    private void validateLengths(CreateTaskListRequest createTaskListRequest) throws AppException{
        if (createTaskListRequest.getName().length() > MAXIMUM_LENGTH_FOR_NAME) {
            throw new AppException(NAME_MAX_LENGTH_MESSAGE, AppErrors.INVALID_VALUE);
        }

        if (Objects.nonNull(createTaskListRequest.getDescription()) && createTaskListRequest.getDescription().length() > MAXIMUM_LENGTH_FOR_DESCRIPTION) {
            throw new AppException(DESCRIPTION_MAX_LENGTH_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }

    private void validateMandatory(String name) throws AppException {
        if (Objects.isNull(name) || name.isBlank() || name.isEmpty()) {
            throw new AppException(NAME_MANDATORY_FIELD_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }
}