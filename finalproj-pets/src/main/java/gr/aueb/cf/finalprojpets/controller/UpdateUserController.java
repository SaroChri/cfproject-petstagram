package gr.aueb.cf.finalprojpets.controller;

import gr.aueb.cf.finalprojpets.dto.UserDTO;
import gr.aueb.cf.finalprojpets.model.User;
import gr.aueb.cf.finalprojpets.repository.UserRepository;
import gr.aueb.cf.finalprojpets.validator.UpdateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class UpdateUserController {

    private final UserRepository userRepository;
    private final UpdateUserValidator updateUserValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UpdateUserController(UserRepository userRepository, UpdateUserValidator updateUserValidator) {
        this.userRepository = userRepository;
        this.updateUserValidator = updateUserValidator;
    }

    @GetMapping("/UpdateForm")
    public String showUpdateAccountPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(),
                user.getCity(), user.getCountry(), user.getEmail(), user.getUsername(),
                "", "");
        model.addAttribute("userDTO", userDTO);
        return "UpdateForm";
    }

    @PostMapping("/UpdateForm")
    public RedirectView updateAccount(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
                                      HttpServletRequest request, Model model) {


        updateUserValidator.validate(userDTO, bindingResult);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        System.out.println(bindingResult); // Debug print
        System.out.println(userDTO); // Debug print



        if (bindingResult.hasErrors()) {
            return new RedirectView("UpdateForm");
        }



        // Update the user fields
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());

        // Update the password only if the new password is provided and matches the confirmed new password
        if (userDTO.getNewPassword() != null && !userDTO.getNewPassword().isEmpty() &&
                userDTO.getNewPassword().equals(userDTO.getConfirmNewPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.getNewPassword())); // use password encoder
        }

        userRepository.save(user);

        // Invalidate the session if the password is updated to force re-authentication
        if (userDTO.getNewPassword() != null && !userDTO.getNewPassword().isEmpty()) {
            request.getSession().invalidate();
        }


        return new RedirectView("/main");
    }
}
