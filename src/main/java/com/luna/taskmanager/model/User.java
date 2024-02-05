package com.luna.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity class representing a user.
 * This class maps to the 'users' table in the database and includes fields for user details such as ID, username, password, and email.
 * It also includes relationships to task lists and tasks that are associated with the user.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    // Unique identifier for the user. It's automatically generated.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username of the user. It is unique and cannot be null.
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // Password for the user account. It is stored as a string and cannot be null.
    @Column(nullable = false, length = 255)
    private String password;

    // Email of the user. It is unique and cannot be null.
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // List of task lists created by the user. It establishes a one-to-many relationship with TaskLists.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskLists> taskLists;

    // List of tasks created by the user. It establishes a one-to-many relationship with Tasks.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tasks> tasks;
}
