package gr.aueb.cf.finalprojpets.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String city;
    private String country;
    private String email;
    private String username;
    @Size(min = 8, message = "Password must have at least ${min} characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?\\d).*$")
    private String password;

    @NotEmpty
    private String confirmPassword;

    public UserDTO(Long id, String username, String password, String firstname, String lastname, String city, String country, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.email = email;
    }
}
