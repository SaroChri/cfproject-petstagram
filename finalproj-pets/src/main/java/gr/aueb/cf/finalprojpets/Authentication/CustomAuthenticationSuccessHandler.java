package gr.aueb.cf.finalprojpets.Authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static final String REDIRECT_URL_SESSION_ATTRIBUTE_NAME = "REDIRECT_URL";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Retrieve the redirect URL from the session attribute
        Object redirectURLObject = request.getSession().getAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME);

        // Check if a redirect URL is present
        if (redirectURLObject != null) {
            // Set the redirect URL as the default target URL
            setDefaultTargetUrl(redirectURLObject.toString());
        } else {
            // Set a default target URL if no redirect URL is present
            setDefaultTargetUrl("/api/main");
        }

        // Remove the redirect URL session attribute
        request.getSession().removeAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME);
        //Call the superclass method to handle the successful authentication
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
