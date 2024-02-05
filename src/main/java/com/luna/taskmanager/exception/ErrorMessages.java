package com.luna.taskmanager.exception;

/**
 * Class containing constant strings for error messages used throughout the application.
 * These messages provide clear, user-friendly explanations for various error conditions.
 */
public class ErrorMessages {

    // Message for when a name field is missing, empty, or exceeds the maximum length
    public static final String NAME_MANDATORY_FIELD_MESSAGE = "Name cannot be null, blank, or empty. Should be of 64 characters max.";

    // Message for when the name exceeds its maximum length of 64 characters
    public static final String NAME_MAX_LENGTH_MESSAGE = "Maximum length for name is 64 characters.";

    // Message for when the description exceeds its maximum length of 256 characters
    public static final String DESCRIPTION_MAX_LENGTH_MESSAGE = "Maximum length for description is 256 characters.";

    // Message indicating a duplicate entry was found for a given name
    public static final String DUPLICATE_ENTRY_FOUND = "Duplicate entry found for name: ";

    // Message for when a UUID field is missing or invalid
    public static final String UUID_MANDATORY_FIELD_MESSAGE = "UUID cannot be null, blank, or empty. Should be a valid UUID of 36 characters.";

    // Message for when a description field is missing
    public static final String DESCRIPTION_MANDATORY_FIELD_MESSAGE = "Description cannot be null. Should be of 256 characters max.";

    // Message indicating the provided UUID is invalid
    public static final String UUID_INVALID_MESSAGE = "Provided UUID is not valid. Should be a valid UUID of 36 characters.";

    // Message for when a requested resource is not found
    public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource with provided UUID either does not exist or has been deleted.";

    // Message indicating missing fields for a task update
    public static final String MISSING_FIELD_FOR_UPDATE = "Either task description or task status or both can be updated. Please provide a value to update.";

    // Message indicating an invalid status value was provided
    public static final String INVALID_STATUS_VALUE_MESSAGE = "Provided value for status is invalid. Allowed values are COMPLETED, IN_PROGRESS, or PENDING.";

    // Message for when a task list is not found
    public static final String TASK_LIST_NOT_FOUND_MESSAGE = "Task List with provided uuid either does not exist or has been deleted.";

    // Message for when a task is not found
    public static final String TASK_NOT_FOUND_MESSAGE = "Task with provided uuid either does not exist or has been deleted.";

    // Message indicating an invalid task status value
    public static final String TASK_STATUS_VALUE_INVALID_MESSAGE = "Provided value for task status is invalid. Allowed values are COMPLETED, IN_PROGRESS or PENDING.";
}
