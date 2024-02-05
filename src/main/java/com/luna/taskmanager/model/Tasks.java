package com.luna.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.type.NumericBooleanConverter;

import java.util.Date;
import java.util.UUID;

/**
 * Entity class representing a task.
 * This class maps to the 'tasks' table in the database and includes fields for task details such as UUID, name, description, status, and its association with task lists and users.
 */
@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tasks extends Auditable {

    // UUID of the task, serves as the primary key.
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    // Name of the task.
    @Column(name = "name")
    private String name;

    // Description of the task.
    @Column(name = "description")
    private String description;

    // Status of the task, represented by the TaskStatus enum.
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // Flag indicating if the task is deleted. It uses a custom converter to map numeric boolean values.
    @Column(name = "is_deleted", insertable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isDeleted;

    // Timestamp of when the task was created.
    @Column(name = "created_at")
    private Date createdAt;

    // Timestamp of the last update to the task.
    @Column(name = "updated_at")
    private Date updatedAt;

    // The task list to which this task belongs.
    @ManyToOne
    @JoinColumn(name="task_list_uuid")
    private TaskLists taskList;

    // The user who created/owns this task.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
