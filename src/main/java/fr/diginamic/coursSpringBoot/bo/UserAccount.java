package fr.diginamic.coursSpringBoot.bo;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ElementCollection (fetch = FetchType.EAGER )
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public UserAccount() {
    }

    public UserAccount(String username, String password, String ... authorities) {
        this.username = username;
        this.password = passwordEncoder().encode(password);
        this.authorities = Arrays.stream(authorities)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }



    /**
     * Getter
     *
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * Getter
     *
     * @return username
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Getter
     *
     * @return password
     **/
    public String getPassword() {
        return password;
    }

    /**
     * Getter
     *
     * @return authorities
     **/
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
