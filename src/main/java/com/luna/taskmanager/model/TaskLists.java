package com.luna.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.type.NumericBooleanConverter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Entity class representing a task list.
 * This class maps to the 'task_lists' table in the database.
 * It includes fields for task list details such as UUID, name, description, and its association with tasks and users.
 */
@Entity
@Table(name = "task_lists")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskLists extends Auditable {

    // UUID of the task list, serves as the primary key.
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    // Name of the task list.
    @Column(name = "name")
    private String name;

    // Description of the task list.
    @Column(name = "description")
    private String description;

    // Flag indicating if the task list is deleted. It uses a custom converter to map numeric boolean values.
    @Column(name = "is_deleted", insertable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isDeleted;

    // Timestamp of when the task list was created.
    @Column(name = "created_at")
    private Date createdAt;

    // Timestamp of the last update to the task list.
    @Column(name = "updated_at")
    private Date updatedAt;

    // List of tasks associated with this task list.
    @OneToMany(mappedBy = "taskList")
    private List<Tasks> tasks;

    // The user who created/owns this task list.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
