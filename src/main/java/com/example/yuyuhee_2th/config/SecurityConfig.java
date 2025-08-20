package com.example.yuyuhee_2th.config;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**").permitAll()                 // Allow access to /public/** without authentication
                        .requestMatchers("/admin/**").hasRole("ADMIN")      // Require ADMIN role for /admin/**
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                        .anyRequest().authenticated()                         // All other requests require authentication
                )
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .ignoringRequestMatchers("/user/**")
                        .ignoringRequestMatchers("/question/**")
                        .ignoringRequestMatchers("/answer/**")
                )
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/", true)
                        // .failureUrl("/user/login?error=true")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutRequestMatcher(new RequestMatcher() {
                            @Override
                            public boolean matches(HttpServletRequest request) {
                                return Objects.equals(request.getRequestURI(), "/user/logout");
                            }
                        })
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}