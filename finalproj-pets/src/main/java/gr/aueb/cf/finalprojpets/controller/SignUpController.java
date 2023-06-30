package gr.aueb.cf.finalprojpets.controller;

import gr.aueb.cf.finalprojpets.dto.UserDTO;
import gr.aueb.cf.finalprojpets.model.User;
import gr.aueb.cf.finalprojpets.service.IUserService;
import gr.aueb.cf.finalprojpets.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SignUpController {

    private final IUserService userService;
    private final UserValidator userValidator;

    @Autowired
    public SignUpController(IUserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    /**
     * Handles GET requests to the "/SignUp" endpoint.
     * Renders the SignUp view with an empty UserDTO object.
     *
     * @param model the model for the view
     * @return the name of the SignUp view
     */

    @GetMapping("/SignUp")
    String SignUp(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "SignUp";
    }

    /**
     * Handles POST requests to the "/SignUp" endpoint.
     * Validates the user input, registers the user, and redirects to the login page on successful registration.
     *
     * @param userDTO       the UserDTO object containing user data
     * @param bindingResult the binding result for validation errors
     * @param model         the model for the view
     * @return the name of the view to render
     */
    @PostMapping("/SignUp")
    public String SignUp(@ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to SignUp
            return "SignUp";
        } else {
            // Register the user
            User signedUpUser = userService.registerUser(userDTO);
            if (signedUpUser != null) {
                // Redirect to the login page on successful registration
                return "redirect:/login";
            }else {
                // If registration fails, add an error message to the model and return to SignUp
                model.addAttribute("error", "Registration failed");
                return "SignUp";
            }
        }
    }
}
