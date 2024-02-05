package com.luna.taskmanager.validations.tasklists;

import com.luna.taskmanager.exception.AppErrors;
import com.luna.taskmanager.exception.AppException;
import com.luna.taskmanager.model.request.UpdateTaskListRequest;
import com.luna.taskmanager.validations.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;


import static com.luna.taskmanager.constants.APIConstants.*;
import static com.luna.taskmanager.exception.ErrorMessages.*;

@Component
public class UpdateTaskListsValidator implements Validator {

    private static final Logger log = (Logger) LoggerFactory.getLogger(UpdateTaskListsValidator.class);

    @Override
    public <T> void validate(T object) throws AppException {
        UpdateTaskListRequest updateTaskListRequest = (UpdateTaskListRequest) object;
        validateMandatory(updateTaskListRequest);
        validateUuid(updateTaskListRequest.getUuid());
        validateLengths(updateTaskListRequest.getDescription());
        log.info("Received request object for task list update has been validated.");
    }

    private void validateLengths(String description) throws AppException {
        if (Objects.nonNull(description) && description.length() > MAXIMUM_LENGTH_FOR_DESCRIPTION) {
            throw new AppException(DESCRIPTION_MAX_LENGTH_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }

    private void validateUuid(String uuid) throws AppException {
        try {
            UUID isValidUUID = UUID.fromString(uuid);
        } catch (IllegalArgumentException  ex) {
            throw new AppException(UUID_INVALID_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }

    private void validateMandatory(UpdateTaskListRequest updateTaskListRequest) throws AppException {
        if (Objects.isNull(updateTaskListRequest.getUuid()) || updateTaskListRequest.getUuid().isBlank() || updateTaskListRequest.getUuid().isEmpty()) {
            throw new AppException(UUID_MANDATORY_FIELD_MESSAGE, AppErrors.INVALID_VALUE);
        }

        if (Objects.isNull(updateTaskListRequest.getDescription())) {
            throw new AppException(DESCRIPTION_MANDATORY_FIELD_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }

}
