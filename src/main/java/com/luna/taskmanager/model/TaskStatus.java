package com.luna.taskmanager.model;
import lombok.*;

/**
 * Enum representing the status of a task.
 * This enum defines the various states a task can be in, such as pending, in progress, or completed.
 */
public enum TaskStatus {
    // Task is yet to be started.
    PENDING("Pending"),

    // Task is currently being worked on.
    IN_PROGRESS("In Progress"),

    // Task has been completed.
    COMPLETED("Completed");

    // The description of the task status.
    private final String status;

    /**
     * Constructor for the enum constants.
     *
     * @param status The description of the status.
     */
    TaskStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the description of the task status.
     *
     * @return The status description.
     */
    public String getStatus() {
        return status;
    }
}
