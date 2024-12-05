package fr.diginamic.coursSpringBoot.config;

import fr.diginamic.coursSpringBoot.bo.UserAccount;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.mapper.UserAccountMapper;
import fr.diginamic.coursSpringBoot.repository.UserAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/","login").permitAll()
                .requestMatchers("/logout").authenticated()
                .requestMatchers("/villes").authenticated()
                .requestMatchers("/villes/supprimerVille/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
                return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {

           return username -> UserAccountMapper.toUserDetails(userAccountRepository.findByUsername(username));
    }
// Solution TP 1
//     public UserDetailsService userDetailsService() {
//        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("pwd")
//                        .roles("USER")
//                        .build());
//
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("pwd")
//                .roles("ADMIN")
//                .build());
//        return userDetailsManager;
//
//    }
}
