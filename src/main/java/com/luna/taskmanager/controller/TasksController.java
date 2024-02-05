package com.luna.taskmanager.controller;

import com.luna.taskmanager.exception.AppException;
import com.luna.taskmanager.model.request.CreateTaskRequest;
import com.luna.taskmanager.model.request.UpdateTaskRequest;
import com.luna.taskmanager.model.responses.TaskResponse;
import com.luna.taskmanager.controller.service.TasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller class for handling tasks-related operations.
 * It includes endpoints for creating, updating, fetching, and deleting tasks.
 */
@RestController
public class TasksController {

    // Logger for this class
    private static final Logger log = LoggerFactory.getLogger(TasksController.class);

    // Service layer dependency for task operations
    @Autowired
    private TasksService tasksService;

    /**
     * Endpoint for creating a new task.
     * @param createTaskRequest Request body containing details for creating a task
     * @param principal The authenticated user's principal
     * @return The created task response
     * @throws AppException If any application-specific exception occurs
     */
    @PostMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT)
    public TaskResponse createTask(@RequestBody CreateTaskRequest createTaskRequest, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task creation.");
            String username = principal.getName();
            return tasksService.createTask(createTaskRequest, username);
        } finally {
            log.info("Processing for task creation request finished.");
        }
    }

    /**
     * Endpoint for updating an existing task.
     * @param updateTaskRequest Request body containing updated details for a task
     * @param principal The authenticated user's principal
     * @return The updated task response
     * @throws AppException If any application-specific exception occurs
     */
    @PutMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT)
    public TaskResponse updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task update.");
            String username = principal.getName();
            return tasksService.updateTask(updateTaskRequest, username);
        } finally {
            log.info("Processing for task update request finished.");
        }
    }

    /**
     * Endpoint for fetching a specific task by its UUID.
     * @param taskUuid The UUID of the task to fetch
     * @param principal The authenticated user's principal
     * @return The requested task response
     * @throws AppException If any application-specific exception occurs
     */
    @GetMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT + "/" + "{task_uuid}")
    public TaskResponse fetchTask(@PathVariable("task_uuid") String taskUuid, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task fetch.");
            String username = principal.getName();
            return tasksService.getTask(taskUuid, username);
        } finally {
            log.info("Processing for task fetch request finished.");
        }
    }

    /**
     * Endpoint for deleting a specific task by its UUID.
     * @param taskUuid The UUID of the task to delete
     * @param principal The authenticated user's principal
     * @throws AppException If any application-specific exception occurs
     */
    @DeleteMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT + "/" + "{task_uuid}")
    public void deleteTask(@PathVariable("task_uuid") String taskUuid, Principal principal) throws AppException {
        try {
            log.info("Incoming request for task delete.");
            String username = principal.getName();
            tasksService.deleteTask(taskUuid, username);
        } finally {
            log.info("Processing for task delete request finished.");
        }
    }

    /**
     * Endpoint for fetching all tasks associated with a specific task list.
     * @param taskListUuid The UUID of the task list for which tasks are fetched
     * @param principal The authenticated user's principal
     * @return A list of tasks responses
     * @throws AppException If any application-specific exception occurs
     */
    @GetMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT + "/" + TASKLIST_ENDPOINT + "/" + "{tasklist_uuid}")
    public List<TaskResponse> fetchTasks(@PathVariable("tasklist_uuid") String taskListUuid, Principal principal) throws AppException {
        try {
            log.info("Incoming request for tasks fetch.");
            String username = principal.getName();
            return tasksService.getTasks(taskListUuid, username);
        } finally {
            log.info("Processing for tasks fetch request finished.");
        }
    }
}