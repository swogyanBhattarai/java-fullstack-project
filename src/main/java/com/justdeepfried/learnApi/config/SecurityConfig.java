package com.justdeepfried.learnApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Custom UserDetailService injected here so that our custom logic is called.
    // Dependency injection doesn't work based on names so it doesn't need to have the same name to be injected.
    // Spring looks for a bean with UserDetailsService. Since CustomUserDetailService implements it, and is the only bean/service, it gets injected.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable()) // Disable cross site request forgery but make session stateless. Any API except GET does not work when enabling csrf.
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST, "/user").permitAll() // Allows adding user without being authenticated.
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Each reload changes the session id.

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    // Custom AuthenticationProvider was created to setPasswordEncoder
    // DaoAuthenticationProvider is the authenticationProvider that ensure database authentication

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
    // Makes the AuthenticationManager available for injection elsewhere, would not have been possible without making a bean and leaving it as a default implementation. Injected in UserService for verify() function.
    // AuthenticationManager calls AuthenticationProvider
}
