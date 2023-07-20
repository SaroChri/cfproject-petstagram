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

    private String password;


    private String confirmPassword;
    private String newPassword;
    private String confirmNewPassword;

    public UserDTO(Long id, String firstname, String lastname, String city, String country, String email, String username, String password, String newPassword) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.email = email;
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }
}
