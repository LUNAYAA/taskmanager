package com.luna.taskmanager.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class representing a request to create a new task list.
 * This class is used to capture and transport data from client-side request to server.
 */
@Getter
@Setter
public class CreateTaskListRequest {

    // The name of the task list. It's annotated with @JsonProperty to map JSON property 'name' to this field.
    @JsonProperty("name")
    private String name;

    // The description of the task list. It's annotated with @JsonProperty to map JSON property 'description' to this field.
    @JsonProperty("description")
    private String description;
}
