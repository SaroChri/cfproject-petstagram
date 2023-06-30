package gr.aueb.cf.finalprojpets.service;

import gr.aueb.cf.finalprojpets.model.User;
import gr.aueb.cf.finalprojpets.dto.UserDTO;
import gr.aueb.cf.finalprojpets.service.exceptions.EntityNotFoundException;

public interface IUserService  {

    /**
     * Register a new user
     * @param userToRegister gathering user information from the UserDto
     * @return the User
     */
    User registerUser(UserDTO userToRegister);

    /**
     * Updates a user
     * @param userDTO
     * @return the updated user
     * @throws EntityNotFoundException if the user's id does not exist
     */
    User updateUser(UserDTO userDTO) throws EntityNotFoundException;

    /**
     * Deletes a user
     * @param id
     * @throws EntityNotFoundException if the user's id does not exist
     */
    void deleteUser(Long id) throws EntityNotFoundException;

    /**
     * Fetches a user using his username
     * @param username
     * @return the user
     * @throws EntityNotFoundException
     */
    User getUserByUsername(String username) throws EntityNotFoundException;

    /**
     * Fetches a user using his id
     * @param id
     * @return the user
     * @throws EntityNotFoundException
     */
    User getUserById(Long id) throws EntityNotFoundException;

    /**
     * Checks if a given username already exists
     * @param username
     * @return true if the username already exists
     */
    boolean usernameAlreadyExists(String username);

}
