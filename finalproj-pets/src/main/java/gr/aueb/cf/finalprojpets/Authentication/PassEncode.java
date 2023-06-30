package gr.aueb.cf.finalprojpets.Authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PassEncode implements PasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        // Encode the raw password using BCryptPasswordEncoder
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Compare the raw password with the encoded password using BCryptPasswordEncoder
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
