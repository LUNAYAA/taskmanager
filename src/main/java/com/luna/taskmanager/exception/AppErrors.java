package com.luna.taskmanager.exception;

/**
 * Enum representing different error types within the application.
 * Each enum constant is associated with a specific error message.
 */
public enum AppErrors {
    // Indicates an invalid value was provided in a request
    INVALID_VALUE("Invalid value"),

    // Indicates a requested resource was not found
    RESOURCE_NOT_FOUND("Resource not found"),

    // Indicates an internal server error occurred
    INTERNAL_SERVER_ERROR("Internal server error"),

    // Indicates a duplicate resource or data was found when it should be unique
    DUPLICATE_FOUND("Duplicate found"),

    // Indicates a user was not found, typically in authentication or user-specific operations
    USER_NOT_FOUND("User not found!");

    // Message associated with the error
    private final String message;

    /**
     * Constructor for the enum constants.
     *
     * @param message The message associated with the error.
     */
    AppErrors(String message) {
        this.message = message;
    }

    /**
     * Retrieves the message associated with the error.
     *
     * @return The error message.
     */
    public String getMessage() {
        return this.message;
    }
}
