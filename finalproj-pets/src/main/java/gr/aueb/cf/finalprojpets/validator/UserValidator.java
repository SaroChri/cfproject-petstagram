package gr.aueb.cf.finalprojpets.validator;

import gr.aueb.cf.finalprojpets.dto.UserDTO;
import gr.aueb.cf.finalprojpets.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;
@Component
public class UserValidator implements Validator {

    private final IUserService userService;

    @Autowired
    public UserValidator(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userToRegister = (UserDTO) target;
        UserDTO userDTO = (UserDTO) target;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty");
        if (userToRegister.getUsername().length() < 3 || userToRegister.getUsername().length() > 30) {
            errors.rejectValue("username", "size");
        }

        if (userService.usernameAlreadyExists(userDTO.getUsername(), currentUsername)) {
            errors.rejectValue("username", "AlreadyExists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty");
        if (userToRegister.getPassword().length() < 8 || userToRegister.getPassword().length() > 30) {
            errors.rejectValue("password", "size");
        }

        if (!Objects.equals(userToRegister.getPassword(), userToRegister.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmation");
        }
    }
}