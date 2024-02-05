package com.luna.taskmanager.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class representing a request to create a new task.
 * This class is used to capture and transport data from the client-side request to the server.
 * It includes task details such as name, description, and the UUID of the task list it belongs to.
 */
@Getter
@Setter
public class CreateTaskRequest {

    // The name of the task. Annotated with @JsonProperty to map the JSON property 'name' to this field.
    @JsonProperty("name")
    private String name;

    // The description of the task. Annotated with @JsonProperty to map the JSON property 'description' to this field.
    @JsonProperty("description")
    private String description;

    // The UUID of the task list to which this task belongs. Annotated with @JsonProperty to map the JSON property 'tasklist_uuid' to this field.
    @JsonProperty("tasklist_uuid")
    private String taskListUuid;
}
