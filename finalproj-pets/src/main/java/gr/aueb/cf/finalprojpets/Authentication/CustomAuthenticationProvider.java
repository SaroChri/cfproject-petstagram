package gr.aueb.cf.finalprojpets.Authentication;

import gr.aueb.cf.finalprojpets.model.User;
import gr.aueb.cf.finalprojpets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Locale;

/**
 * An authentication provider used to compare if the user credentials
 * agree with those stored in the UserRepository and
 * authenticate the user if the values are equal.
 */

@Component
public class CustomAuthenticationProvider implements  AuthenticationProvider {

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    /**
     * CustomAuthenticationProvider constructor.
     *
     * @param userRepository  The UserRepository used to retrieve user information.
     * @param messageSource   The MessageSource used for accessing localized messages.
     */
    @Autowired
    public CustomAuthenticationProvider(UserRepository userRepository, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    private MessageSourceAccessor accessor;

    /**
     * Initializes the MessageSourceAccessor using the provided MessageSource.
     * This method is executed after the bean has been constructed and dependencies have been injected.
     */
    @PostConstruct
    private void init() {

        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }
    /**
     * Authenticates the user based on the provided authentication object.
     *
     * @param authentication The authentication object containing the user's credentials.
     * @return An authenticated UsernamePasswordAuthenticationToken if the user is authenticated.
     * @throws AuthenticationException If the authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Extract the username and password from the authentication object
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Retrieve the user from the UserRepository based on the username
        User user = userRepository.findByUsername(username);

        // Check if the user exists
        if (user == null) {
            throw new BadCredentialsException(accessor.getMessage("badCredentials"));
        }
        // Create a new instance of PasswordEncoder

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Compare the provided password with the encoded password of the user
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(accessor.getMessage("badCredentials"));
        }
        // Create and return a new instance of UsernamePasswordAuthenticationToken
        // with the username, password, and an empty list of granted authorities
        return new UsernamePasswordAuthenticationToken(username, password, Collections.<GrantedAuthority>emptyList());
    }

    /**
     * Checks if the authentication provider supports the provided authentication class.
     *
     * @param authentication The authentication class to be checked.
     * @return true if the authentication class is assignable to UsernamePasswordAuthenticationToken, false otherwise.
     */

        @Override
        public boolean supports (Class < ? > authentication){
            // Check if the authentication object is assignable to UsernamePasswordAuthenticationToken
            return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
        }

}


