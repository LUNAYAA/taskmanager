package com.luna.taskmanager.repository;

import com.luna.taskmanager.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Tasks entity.
 * This interface provides methods to perform various operations on the tasks table, such as finding, saving, and deleting tasks.
 */
@Repository
public interface TasksRepository extends JpaRepository<Tasks, UUID> {

    /**
     * Custom query to find a task by its UUID and user's ID.
     *
     * @param uuid The UUID of the task.
     * @param userId The ID of the user associated with the task.
     * @return An Optional containing the task if found, or an empty Optional otherwise.
     */
    @Query("SELECT tl FROM TaskLists tl WHERE tl.uuid = :uuid AND tl.user.id = :userId")
    Optional<Tasks> findByIdAndUserId(@Param("uuid") UUID uuid, @Param("userId") Long userId);

    /**
     * Finds tasks by the task list UUID, user's ID, and deletion status.
     * This method is useful for filtering tasks based on their association with a task list and user, and whether or not they are marked as deleted.
     *
     * @param taskListUuid The UUID of the task list.
     * @param userId The ID of the user associated with the tasks.
     * @param isDeleted The deletion status of the tasks.
     * @return A List of tasks matching the criteria.
     */
    List<Tasks> findByTaskListUuidAndUserIdAndIsDeleted(UUID taskListUuid, Long userId, boolean isDeleted);
}
