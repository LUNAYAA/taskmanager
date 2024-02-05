package com.luna.taskmanager.model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class representing an error response.
 * This class is used to structure the response sent back to the client in case of errors.
 * It includes a code representing the type of error and a message providing more details about the error.
 */
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    // The code representing the type of error.
    // Annotated with @JsonProperty to map the JSON property 'code' to this field.
    @JsonProperty("code")
    private String code;

    // A descriptive message about the error.
    // Annotated with @JsonProperty to map the JSON property 'message' to this field.
    @JsonProperty("message")
    private String message;
}
