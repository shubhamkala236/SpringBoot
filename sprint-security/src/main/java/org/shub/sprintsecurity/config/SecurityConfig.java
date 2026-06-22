package org.shub.sprintsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsPasswordService userDetailsPasswordService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        http.csrf(c -> c.disable())
            .authorizeHttpRequests(req->req.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//    @Bean
//    public AuthenticationProvider authProvider()
//    {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsPasswordService(userDetailsPasswordService);
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//
//        return provider;
//    }

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user = User
//                            .withDefaultPasswordEncoder()
//                            .username("shubham")
//                            .password("user")
//                            .roles("USER")
//                            .build();
//
//        UserDetails admin = User
//                            .withDefaultPasswordEncoder()
//                            .username("admin")
//                            .password("admin")
//                            .roles("ADMIN")
//                            .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }
}

