package com.luna.taskmanager.repository;

import com.luna.taskmanager.model.TaskLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the TaskLists entity.
 * This interface provides methods to perform various operations on the task_lists table, such as finding, saving, and deleting task lists.
 */
@Repository
public interface TaskListsRepository extends JpaRepository<TaskLists, UUID> {

    /**
     * Finds a task list by its name.
     *
     * @param name The name of the task list.
     * @return An Optional containing the task list if found, or an empty Optional otherwise.
     */
    Optional<TaskLists> findByName(String name);

    /**
     * Finds a task list by its name and user's ID.
     *
     * @param name The name of the task list.
     * @param userId The ID of the user.
     * @return An Optional containing the task list if found, or an empty Optional otherwise.
     */
    Optional<TaskLists> findByNameAndUserId(String name, Long userId);

    /**
     * Custom query to find a task list by its UUID and user's ID.
     *
     * @param uuid The UUID of the task list.
     * @param userId The ID of the user.
     * @return An Optional containing the task list if found, or an empty Optional otherwise.
     */
    @Query("SELECT tl FROM TaskLists tl WHERE tl.uuid = :uuid AND tl.user.id = :userId")
    Optional<TaskLists> findByIdAndUserId(@Param("uuid") UUID uuid, @Param("userId") Long userId);

    /**
     * Finds all task lists associated with a given user's ID.
     *
     * @param id The ID of the user.
     * @return A List of task lists associated with the user.
     */
    List<TaskLists> findAllByUserId(Long id);
}
