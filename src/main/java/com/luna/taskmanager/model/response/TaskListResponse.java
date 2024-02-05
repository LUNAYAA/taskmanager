package com.luna.taskmanager.model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

/**
 * Model class representing a response for a task list.
 * This class is used to structure the data sent back to the client, typically when a task list is created, updated, or retrieved.
 * It includes details such as the UUID, name, and description of the task list.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TaskListResponse {

    // The UUID of the task list.
    // Annotated with @JsonProperty to map the JSON property 'uuid' to this field.
    @JsonProperty("uuid")
    private UUID uuid;

    // The name of the task list.
    // Annotated with @JsonProperty to map the JSON property 'name' to this field.
    @JsonProperty("name")
    private String name;

    // The description of the task list.
    // Annotated with @JsonProperty to map the JSON property 'description' to this field.
    @JsonProperty("description")
    private String description;
}
