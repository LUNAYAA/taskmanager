package com.luna.taskmanager.exception;

import lombok.*;

/**
 * Custom exception class for the application.
 * Extends Exception and includes additional details specific to the application context.
 */
@Setter
@Getter
@NoArgsConstructor
public class AppException extends Exception {
    // Enumeration value representing the type of error.
    private AppErrors errorCode;

    // Custom message for the exception.
    private String message;

    /**
     * Constructor to create an AppException with only an error code.
     * The message is set based on the error code.
     *
     * @param errorCode The specific application error associated with this exception.
     */
    public AppException(AppErrors errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Constructor to create an AppException with both a custom message and an error code.
     *
     * @param message The custom message for the exception.
     * @param errorCode The specific application error associated with this exception.
     */
    public AppException(String message, AppErrors errorCode) {
        super(message); // Call to the superclass (Exception) constructor to set the message.
        this.errorCode = errorCode;
        this.message = message;
    }
}
