package com.luna.taskmanager.converters;

import com.luna.taskmanager.model.TaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * JPA converter to handle the conversion between the TaskStatus enum and
 * its database representation.
 * This converter automatically applies to all entity attributes of type TaskStatus.
 */
@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {

    /**
     * Converts a TaskStatus enum to its database column representation.
     *
     * @param taskStatus The TaskStatus enum to convert
     * @return A String representing the TaskStatus in the database,
     *         or null if the input is null.
     */
    @Override
    public String convertToDatabaseColumn(TaskStatus taskStatus) {
        if (taskStatus == null) {
            return null;
        }
        return taskStatus.getStatus();
    }

    /**
     * Converts a database column value to a TaskStatus enum.
     *
     * @param taskStatus The database column value as a String
     * @return The corresponding TaskStatus enum,
     *         or throws an IllegalArgumentException if the value is not valid.
     */
    @Override
    public TaskStatus convertToEntityAttribute(String taskStatus) {
        if (taskStatus == null) {
            return null;
        }
        return Stream.of(TaskStatus.values())
                .filter(c -> c.getStatus().equals(taskStatus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
