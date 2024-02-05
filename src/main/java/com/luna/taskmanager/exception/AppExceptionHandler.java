package com.luna.taskmanager.exception;
import com.luna.taskmanager.model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.luna.taskmanager.exception.AppErrors.*;

/**
 * Global exception handler for the application.
 * It handles exceptions thrown by the controllers and converts them into appropriate HTTP responses.
 */
@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * Handles exceptions of type AppException.
     * Maps different types of AppErrors to corresponding HTTP status codes.
     *
     * @param ex The AppException that was thrown.
     * @return ResponseEntity containing the error details and appropriate HTTP status.
     */
    @ExceptionHandler({AppException.class})
    public ResponseEntity<?> handleAppException(AppException ex) {
        // Building the error response based on the exception details
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode().name())
                .message(ex.getMessage())
                .build();

        // Switch case to map the AppError to the corresponding HTTP status
        switch (ex.getErrorCode()) {
            case INVALID_VALUE:
                return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
            case RESOURCE_NOT_FOUND:
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            case INTERNAL_SERVER_ERROR:
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            case DUPLICATE_FOUND:
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            default:
                // Default case to handle any unanticipated errors
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
