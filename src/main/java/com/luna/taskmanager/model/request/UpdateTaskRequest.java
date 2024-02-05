package com.luna.taskmanager.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class representing a request to update an existing task.
 * This class is used to capture and transport data from the client-side request to the server.
 * It includes the task's UUID, new description, and status to be updated.
 */
@Setter
@Getter
public class UpdateTaskRequest {

    // The UUID of the task to be updated.
    // Annotated with @JsonProperty to map the JSON property 'uuid' to this field.
    @JsonProperty("uuid")
    private String uuid;

    // The new description for the task.
    // Annotated with @JsonProperty to map the JSON property 'description' to this field.
    @JsonProperty("description")
    private String description;

    // The new status of the task.
    // Annotated with @JsonProperty to map the JSON property 'status' to this field.
    @JsonProperty("status")
    private String status;
}
