package com.luna.taskmanager.controller.service;

import com.luna.taskmanager.exception.AppException;
import com.luna.taskmanager.model.TaskLists;
import com.luna.taskmanager.model.User;
import com.luna.taskmanager.model.request.CreateTaskListRequest;
import com.luna.taskmanager.model.request.UpdateTaskListRequest;
import com.luna.taskmanager.model.responses.TaskListResponse;
import com.luna.taskmanager.repository.TaskListsRepository;
import com.luna.taskmanager.repository.UserRepository;
import com.luna.taskmanager.validations.tasklists.CreateTaskListsValidator;
import com.luna.taskmanager.validations.tasklists.GetTaskListsValidator;
import com.luna.taskmanager.validations.tasklists.UpdateTaskListsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.luna.taskmanager.exception.AppErrors.*;
import static com.luna.taskmanager.exception.ErrorMessages.*;

/**
 * Service class handling business logic for task list operations.
 * Provides methods for creating, updating, retrieving, and deleting task lists.
 */
@Service
public class TaskListsService {

    private static final Logger log = LoggerFactory.getLogger(TaskListsService.class);

    @Autowired
    private TaskListsRepository taskListsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateTaskListsValidator createTaskListsValidator;

    @Autowired
    private UpdateTaskListsValidator updateTaskListsValidator;

    @Autowired
    private GetTaskListsValidator getTaskListsValidator;

    /**
     * Creates a new task list based on the request and username.
     * Validates the request, fetches the user, and saves the new task list to the database.
     *
     * @param request The request containing the task list details.
     * @param username The username of the user creating the task list.
     * @return The response containing the details of the created task list.
     * @throws AppException If validation fails or user is not found.
     */

    public TaskListResponse createTaskList(CreateTaskListRequest request, String username) throws AppException {
        createTaskListsValidator.validate(request);
        User user = userRepository.findUsername(username)
                .orElseThrow(() -> new AppException("User not found", USER_NOT_FOUND));

        checkExistence(request.getName(), user.getId());

        TaskLists taskList = new TaskLists();
        taskList.setUuid(UUID.randomUUID());
        taskList.setName(request.getName());
        taskList.setDescription(request.getDescription());
        taskList.setCreatedAt(new Date());
        taskList.setUpdatedAt(new Date());
        taskList.setUser(user);

        TaskLists savedTaskList = taskListsRepository.save(taskList);
        return convertToTaskListResponse(savedTaskList);
    }

    /**
     * Updates an existing task list based on the request and username.
     * Validates the request, fetches the user, fetches the existing task list, and updates it in the database.
     *
     * @param request The request containing the updated task list details.
     * @param username The username of the user updating the task list.
     * @return The response containing the details of the updated task list.
     * @throws AppException If validation fails, user or task list is not found.
     */

    public TaskListResponse updateTaskList(UpdateTaskListRequest request, String username) throws AppException {
        updateTaskListsValidator.validate(request);
        User user = userRepository.findUsername(username)
                .orElseThrow(() -> new AppException("User not found", USER_NOT_FOUND));


        TaskLists taskList = taskListsRepository.findByIdAndUserId(UUID.fromString(request.getUuid()), user.getId())
                .orElseThrow(() -> new AppException("Task list not found", RESOURCE_NOT_FOUND));

        taskList.setDescription(request.getDescription());
        taskList.setUpdatedAt(new Date());

        TaskLists updatedTaskList = taskListsRepository.save(taskList);
        return convertToTaskListResponse(updatedTaskList);
    }

    /**
     * Retrieves a specific task list based on its UUID and the username.
     * Validates the UUID, fetches the user, and retrieves the task list from the database.
     *
     * @param taskListUuid The UUID of the task list to retrieve.
     * @param username The username of the user requesting the task list.
     * @return The response containing the requested task list details.
     * @throws AppException If validation fails, user or task list is not found.
     */

    public TaskListResponse getTaskList(String taskListUuid, String username) throws AppException {
        getTaskListsValidator.validate(taskListUuid);
        User user = userRepository.findUsername(username)
                .orElseThrow(() -> new AppException("User not found", USER_NOT_FOUND));

        TaskLists taskList = taskListsRepository.findByIdAndUserId(UUID.fromString(taskListUuid), user.getId())
                .orElseThrow(() -> new AppException("Task list not found", RESOURCE_NOT_FOUND));

        return convertToTaskListResponse(taskList);
    }
    /**
     * Retrieves all task lists associated with a given username.
     * Fetches the user and retrieves all their task lists from the database.
     *
     * @param username The username of the user whose task lists are to be retrieved.
     * @return A list of responses containing the details of all task lists associated with the user.
     * @throws AppException If the user is not found.
     */
    public List<TaskListResponse> getAllTaskLists(String username) throws AppException {
        User user = userRepository.findUsername(username)
                .orElseThrow(() -> new AppException("User not found", USER_NOT_FOUND));

        List<TaskLists> taskLists = taskListsRepository.findAllByUserId(user.getId());
        return taskLists.stream()
                .map(this::convertToTaskListResponse)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a specific task list based on its UUID and the username.
     * Validates the UUID, fetches the user, and marks the task list as deleted in the database.
     *
     * @param taskListUuid The UUID of the task list to delete.
     * @param username The username of the user requesting the deletion.
     * @throws AppException If validation fails, user or task list is not found.
     */

    public void deleteTaskList(String taskListUuid, String username) throws AppException {
        getTaskListsValidator.validate(taskListUuid);
        User user = userRepository.findUsername(username)
                .orElseThrow(() -> new AppException("User not found", USER_NOT_FOUND));

        TaskLists taskList = taskListsRepository.findByIdAndUserId(UUID.fromString(taskListUuid), user.getId())
                .orElseThrow(() -> new AppException("Task list not found", RESOURCE_NOT_FOUND));

        taskList.setIsDeleted(true);
        taskList.setUpdatedAt(new Date());
        taskListsRepository.save(taskList);
    }

    private TaskListResponse convertToTaskListResponse(TaskLists taskList) {
        return TaskListResponse.builder()
                .uuid(UUID.fromString(taskList.getUuid().toString()))
                .name(taskList.getName())
                .description(taskList.getDescription())
                .build();
    }

    private void checkExistence(String name, Long userId) throws AppException {
        Optional<TaskLists> taskListsOptional = taskListsRepository.findByNameAndUserId(name, userId);
        if (taskListsOptional.isPresent() && !taskListsOptional.get().getIsDeleted()) {
            log.info("Task list with name : " + name + " already exist for the user. Sending error response.");
            throw new AppException(DUPLICATE_ENTRY_FOUND + name, DUPLICATE_FOUND);
        }
    }
}
