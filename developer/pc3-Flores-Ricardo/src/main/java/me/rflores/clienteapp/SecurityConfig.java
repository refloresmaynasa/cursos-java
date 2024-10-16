package me.rflores.clienteapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/api/*")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/clientes/**").authenticated()  // Protect endpoints
                        .anyRequest().permitAll()  // Allow other requests without authentication
                )
                .formLogin(form -> form
                        .permitAll()  // Enable form login for all users
                )
                .logout(logout -> logout
                        .permitAll()  // Allow logout for all users
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")  // Use {noop} to indicate plain text password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
