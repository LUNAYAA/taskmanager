package com.luna.taskmanager.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class representing a request to update an existing task list.
 * This class is used to capture and transport the required data from the client-side request to the server.
 * It includes the task list's UUID and the new description to be updated.
 */
@Getter
@Setter
public class UpdateTaskListRequest {

    // The UUID of the task list to be updated.
    // Annotated with @JsonProperty to map the JSON property 'uuid' to this field.
    @JsonProperty("uuid")
    private String uuid;

    // The new description for the task list.
    // Annotated with @JsonProperty to map the JSON property 'description' to this field.
    @JsonProperty("description")
    private String description;
}
