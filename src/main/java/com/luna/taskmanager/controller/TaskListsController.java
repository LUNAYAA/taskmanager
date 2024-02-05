package com.luna.taskmanager.controller;

import com.luna.taskmanager.exception.AppException;
import com.luna.taskmanager.model.request.CreateTaskListRequest;
import com.luna.taskmanager.model.request.UpdateTaskListRequest;
import com.luna.taskmanager.model.responses.TaskListResponse;
import com.luna.taskmanager.controller.service.TaskListsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller for handling task list related requests.
 * Provides endpoints for creating, updating, retrieving, and deleting task lists.
 */
@RestController
public class TaskListsController {

    // Logger for this class
    private static final Logger log = LoggerFactory.getLogger(TaskListsController.class);

    // Service layer dependency for task list operations
    @Autowired
    private TaskListsService taskListsService;

    /**
     * Endpoint to create a new task list.
     * @param createTaskListRequest Request body containing task list details
     * @param principal Authenticated user's principal
     * @return Created task list response
     * @throws AppException If any application-specific exception occurs
     */
    @PostMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT)
    public TaskListResponse createTaskList(@RequestBody CreateTaskListRequest createTaskListRequest, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task list creation.");
            String username = principal.getName();
            return taskListsService.createTaskList(createTaskListRequest, username);
        } finally {
            log.info("Processing for task list creation request finished.");
        }
    }

    /**
     * Endpoint to update an existing task list.
     * @param updateTaskListRequest Request body containing updated task list details
     * @param principal Authenticated user's principal
     * @return Updated task list response
     * @throws AppException If any application-specific exception occurs
     */
    @PutMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT)
    public TaskListResponse updateTaskList(@RequestBody UpdateTaskListRequest updateTaskListRequest, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task list update.");
            String username = principal.getName();
            return taskListsService.updateTaskList(updateTaskListRequest, username);
        } finally {
            log.info("Processing for task list update request finished.");
        }
    }

    /**
     * Endpoint to retrieve a specific task list by its UUID.
     * @param taskListUuid The UUID of the task list
     * @param principal Authenticated user's principal
     * @return The requested task list response
     * @throws AppException If any application-specific exception occurs
     */
    @GetMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT + "/" + "{task_list_uuid}")
    public TaskListResponse getTaskList(@PathVariable("task_list_uuid") String taskListUuid, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task list fetch.");
            String username = principal.getName();
            return taskListsService.getTaskList(taskListUuid, username);
        } finally {
            log.info("Processing for task list get request finished.");
        }
    }

    /**
     * Endpoint to delete a specific task list by its UUID.
     * @param taskListUuid The UUID of the task list to be deleted
     * @param principal Authenticated user's principal
     * @throws AppException If any application-specific exception occurs
     */
    @DeleteMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT + "/" + "{task_list_uuid}")
    public void deleteTaskList(@PathVariable("task_list_uuid") String taskListUuid, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task list delete.");
            String username = principal.getName();
            taskListsService.deleteTaskList(taskListUuid, username);
        } finally {
            log.info("Processing for task list delete request finished.");
        }
    }
}