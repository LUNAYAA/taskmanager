package com.luna.taskmanager.repository;

import com.luna.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 * This interface provides methods for performing various operations related to User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if a user with the given username exists in the database.
     *
     * @param username The username to check for existence.
     * @return true if a user with the given username exists, false otherwise.
     */
    boolean existsByUsername(String username);

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The User entity if found, null otherwise.
     */
    User findByUsername(String username);

    /**
     * Checks if a user with the given email exists in the database.
     *
     * @param email The email to check for existence.
     * @return true if a user with the given email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Custom query to find a user by their username.
     * This method returns an Optional containing the user if found.
     *
     * @param username The username of the user to find.
     * @return An Optional containing the User entity if found, or an empty Optional otherwise.
     */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUsername(@Param("username") String username);
}
