package gr.aueb.cf.finalprojpets.validator;

import gr.aueb.cf.finalprojpets.dto.UserDTO;
import gr.aueb.cf.finalprojpets.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;


@Component
public class UpdateUserValidator implements Validator {

    private final IUserService userService;

    @Autowired
    public UpdateUserValidator(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty");
        if (userDTO.getUsername().length() < 3 || userDTO.getUsername().length() > 30) {
            errors.rejectValue("username", "size");
        }
        if (userService.usernameAlreadyExists(userDTO.getUsername(), currentUsername)) {
            errors.rejectValue("username", "AlreadyExists");
        }

        // Password validation for the update operation
        if (StringUtils.isNotBlank(userDTO.getNewPassword())) {
            if (userDTO.getNewPassword().length() < 8 || userDTO.getNewPassword().length() > 30) {
                errors.rejectValue("newPassword", "size");
            }

            if (!Objects.equals(userDTO.getNewPassword(), userDTO.getConfirmNewPassword())) {
                errors.rejectValue("confirmNewPassword", "confirmation");
            }
        }
    }

//    public boolean isOldPasswordValid(String oldPassword) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//        UserDTO currentUser = userService.findByUsername(currentUsername);
//        return passwordEncoder.matches(oldPassword, currentUser.getPassword());
//    }
}
