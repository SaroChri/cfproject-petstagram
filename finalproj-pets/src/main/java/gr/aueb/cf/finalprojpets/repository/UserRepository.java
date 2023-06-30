package gr.aueb.cf.finalprojpets.repository;


import gr.aueb.cf.finalprojpets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    /**
     * Finds a user by username.
     *
     * @param username The username to search for.
     * @return The User object if found.
     */
    User findByUsername(String username);

    /**
     * Finds a user by his id
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * Performs user validation based on the provided username and password.
     *
     * @param username The username to validate.
     * @param password The password to validate.
     * @return true if the username and password combination is valid.
     */

    @Query("SELECT count(*) > 0 FROM User U WHERE U.username = ?1 AND U.password = ?2")
    boolean UserValidation(String username, String password);

    /**
     * Checks if a username already exists in the repository.
     *
     * @param username The username to check.
     * @return true if the username exists.
     */
    @Query("SELECT count(*) > 0 FROM User U WHERE U.username = ?1")
    boolean UsernameExists(String username);


}
