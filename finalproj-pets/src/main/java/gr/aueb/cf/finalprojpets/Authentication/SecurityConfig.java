package gr.aueb.cf.finalprojpets.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new PassEncode();
    }

    private final CustomAuthenticationProvider authProvider;

    @Autowired
    public SecurityConfig(CustomAuthenticationProvider authProvider) {

        this.authProvider = authProvider;
    }


    /**
     * Configures the authentication manager with the custom authentication provider.
     *
     * @param auth the AuthenticationManagerBuilder used for authentication configuration
     */


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authProvider);
    }

    /**
     * Configures the security filters for HTTP requests.
     *
     * @param http the HttpSecurity object for configuring security
     * @return the SecurityFilterChain object representing the security filter chain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/login", "/SignUp").permitAll()
                .and()
                .authorizeRequests().antMatchers("/main").authenticated()
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/login").defaultSuccessUrl("/main").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .httpBasic();

        return http.build();
    }

    /**
     * Configures custom security options for ignoring specific URLs.
     *
     * @return the WebSecurityCustomizer object for customizing web security
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/static/**");
    }

    /**
     * Creates the authentication manager bean.
     *
     * @param authenticationConfiguration the AuthenticationConfiguration used for creating the authentication manager
     * @return the AuthenticationManager bean
     * @throws Exception if an error occurs during authentication manager creation
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
