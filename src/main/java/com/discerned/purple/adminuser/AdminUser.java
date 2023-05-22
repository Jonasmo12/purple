package com.discerned.purple.adminuser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "admin_user")
@Getter
@Setter
@NoArgsConstructor
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long Id;
    private String role;
    private String firstName;
    private String lastName;
    private String title;
    private String username;
    private String password;
    private Boolean locked = false;
    private Boolean enabled = true;

    public AdminUser(
            String role,
            String firstName,
            String lastName,
            String title,
            String username,
            String password
    ) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.username = username;
        this.password = password;
    }
}
