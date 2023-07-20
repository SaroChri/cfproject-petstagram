package gr.aueb.cf.finalprojpets.controller;


import gr.aueb.cf.finalprojpets.model.User;
import gr.aueb.cf.finalprojpets.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteUserAccount {
    private final UserRepository userRepository;

    public DeleteUserAccount(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/deleteAccount")
    public String showDeleteAccountPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Retrieve the user from the repository using the username
        User user = userRepository.findByUsername(username);

        if (user != null) {
            model.addAttribute("user", user); // Pass the entire user object to the template
            return "deleteAccount";
        } else {
            // Handle the case where the user is not found
            // Redirect to an error page or display an error message
            return "error";
        }
    }


    @PostMapping("/users/delete")
    public RedirectView deleteAccount(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Retrieve the user from the repository using the username
        User user = userRepository.findByUsername(username);

        if (user != null) {
            // Delete the user from the repository
            userRepository.deleteById(user.getId());

            // Invalidate the session to log the user out
            request.getSession().invalidate();

            // Redirect to the login page
            return new RedirectView("/login");
        } else {
            // Handle the case where the user is not found
            // Redirect to an error page or display an error message
            return new RedirectView("/error");
        }
    }
}
