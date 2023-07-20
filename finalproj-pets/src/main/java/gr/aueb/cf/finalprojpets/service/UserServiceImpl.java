package gr.aueb.cf.finalprojpets.service;

import gr.aueb.cf.finalprojpets.dto.UserDTO;
import gr.aueb.cf.finalprojpets.model.User;
import gr.aueb.cf.finalprojpets.repository.UserRepository;
import gr.aueb.cf.finalprojpets.service.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    /**
     * Registers a new user.
     */
    @Transactional
    @Override
    public User registerUser(UserDTO userToRegister) {
            User user = new User();
            user.setUsername(userToRegister.getUsername());
            user.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
            user.setFirstname(userToRegister.getFirstname());
            user.setLastname(userToRegister.getLastname());
            user.setCity(userToRegister.getCity());
            user.setCountry(userToRegister.getCountry());
            user.setEmail(userToRegister.getEmail());

        return userRepository.save(user);
    }
    /**
     * Updates an existing user.
     *
     * @param userDTO the UserDTO object containing updated user data
     * @return the updated User object
     * @throws EntityNotFoundException if the user with the specified ID is not found
     */
    @Override
    @Transactional
    public User updateUser(UserDTO userDTO) throws EntityNotFoundException {
        User user = userRepository.findUserById(userDTO.getId());
        if (user == null) throw new EntityNotFoundException(User.class, userDTO.getId());
        return userRepository.save(convertToUsers(userDTO));
    }
    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     */
    @Transactional
    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new EntityNotFoundException(User.class,id);
        }
        userRepository.delete(user);
    }
    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to retrieve
     * @return the User object with the specified username
     * @throws EntityNotFoundException if the user with the specified username is not found
     */
    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new EntityNotFoundException(User.class, null);
        }
        return user;
    }


    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User object with the specified ID
     * @throws EntityNotFoundException if the user with the specified ID is not found
     */
    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        Optional<User> user;
        user = userRepository.findById(id);
        if (user.isEmpty()) throw new EntityNotFoundException(User.class, 0L);
        return  user.get();
    }

    /**
     * Checks if a username already exists.
     *
     * @param username the username to check
     * @return true if the username already exists, false otherwise
     */
    @Override
    public boolean usernameAlreadyExists(String username, String currentUsername) {

        User user = userRepository.findByUsername(username);
        if (user == null) return false;
        return !user.getUsername().equals(currentUsername);
    }
    /**
     * Converts a UserDTO object to a User object.
     *
     * @param dto the UserDTO object to convert
     * @return the converted User object
     */
    private static User convertToUsers(UserDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getFirstname()
                        ,dto.getLastname(), dto.getCountry(), dto.getCity(), dto.getEmail());
    }
}
