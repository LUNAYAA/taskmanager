package com.luna.taskmanager.validations.tasks;


import com.luna.taskmanager.exception.AppErrors;
import com.luna.taskmanager.exception.AppException;
import com.luna.taskmanager.validations.Validator;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static com.luna.taskmanager.exception.ErrorMessages.*;

@Component
public class GetTaskValidator implements Validator {

    @Override
    public <T> void validate(T object) throws AppException {
        String taskUuid = (String) object;
        validateMandatory(taskUuid);
        validateUuid(taskUuid);
    }

    private void validateMandatory(String taskUuid) throws AppException {
        if (Objects.isNull(taskUuid) || taskUuid.isBlank() || taskUuid.isEmpty()) {
            throw new AppException(UUID_MANDATORY_FIELD_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }

    private void validateUuid(String uuid) throws AppException {
        try {
            UUID isValidUUID = UUID.fromString(uuid);
        } catch (IllegalArgumentException  ex) {
            throw new AppException(UUID_INVALID_MESSAGE, AppErrors.INVALID_VALUE);
        }
    }
}
