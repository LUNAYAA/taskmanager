package com.luna.taskmanager.model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pk.SimpleToDos.model.TaskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Model class representing a response for a task.
 * This class is structured to send back task-related data to the client, typically when a task is created, updated, or retrieved.
 * It includes details such as the UUID, name, description, status, and the UUID of the task list it belongs to.
 */
@Getter
@Setter
@Builder
public class TaskResponse {

    // The UUID of the task.
    // Annotated with @JsonProperty to map the JSON property 'uuid' to this field.
    @JsonProperty("uuid")
    private UUID uuid;

    // The name of the task.
    // Annotated with @JsonProperty to map the JSON property 'name' to this field.
    @JsonProperty("name")
    private String name;

    // The description of the task.
    // Annotated with @JsonProperty to map the JSON property 'description' to this field.
    @JsonProperty("description")
    private String description;

    // The status of the task, represented by the TaskStatus enum.
    // Annotated with @JsonProperty to map the JSON property 'status' to this field.
    @JsonProperty("status")
    private TaskStatus status;

    // The UUID of the task list to which this task belongs.
    // Annotated with @JsonProperty to map the JSON property 'tasklist_uuid' to this field.
    @JsonProperty("tasklist_uuid")
    private UUID taskListUuid;
}
