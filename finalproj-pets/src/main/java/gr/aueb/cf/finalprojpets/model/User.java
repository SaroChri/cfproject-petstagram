package gr.aueb.cf.finalprojpets.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;
    @Column(name = "LASTNAME", nullable = false)
    private String lastname;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    public User(Long id, String username, String password, String firstname, String lastname, String city, String country, String email) {
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

