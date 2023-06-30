package gr.aueb.cf.finalprojpets.controller;

import gr.aueb.cf.finalprojpets.Authentication.CustomAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Controller responsible for handling login-related requests.
 */
@Controller
public class LoginController {

    /**
     * Handles the "/login" endpoint.
     *
     * @param model     The Model object for adding attributes.
     * @param principal The Principal object representing the authenticated user.
     * @param request   The HttpServletRequest object representing the current request.
     * @return The name of the login view template or a redirect to the main page.
     * @throws Exception If an exception occurs during the request processing.
     */
    @GetMapping(path = "/login")
    String login(Model model, Principal principal, HttpServletRequest request) throws Exception {

        String referer = request.getHeader("Referer");
        request.getSession().setAttribute(CustomAuthenticationSuccessHandler.REDIRECT_URL_SESSION_ATTRIBUTE_NAME,
                referer);
        // Check if the principal is null (user not authenticated)
        if (principal == null) {
            return "login";
        } else {
            return "redirect:/main";
        }
    }

    /**
     * Handles the root ("/") endpoint.
     *
     * @param model     The Model object for adding attributes.
     * @param principal The Principal object representing the authenticated user.
     * @param request   The HttpServletRequest object representing the current request.
     * @return The name of the login view template or a redirect to the main page.
     * @throws Exception If an exception occurs during the request processing.
     */
    @GetMapping(path = "/")
    String root(Model model, Principal principal, HttpServletRequest request) throws Exception {
        if (principal == null) {
            return "login";
        } else {
            return "redirect:/main";
        }
    }
}
