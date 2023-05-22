package com.discerned.purple.adminuser;

import com.discerned.purple.auth.PurpleUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "AdminUser")
@Getter
@Setter
@NoArgsConstructor
public class AdminUser extends PurpleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private UUID id;
    private String role = "ROLE_ADMIN";
    private String firstName;
    private String lastName;
    private String title;

    public AdminUser(
            String role,
            String firstName,
            String lastName,
            String title,
            String username,
            String password
    ) {
        super(role, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }
}
