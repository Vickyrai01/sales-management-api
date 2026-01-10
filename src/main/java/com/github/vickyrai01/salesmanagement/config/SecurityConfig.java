package com.github.vickyrai01.salesmanagement.config;

import com.github.vickyrai01.salesmanagement.service.auth.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserService userService;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, UserService userService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.GET, "/api/branch/**").hasAnyRole("ADMIN", "STOCK_MANAGER")
                    .requestMatchers("/api/branch/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/product/**").hasAnyRole("ADMIN", "STOCK_MANAGER")
                    .requestMatchers("/api/product/**").hasRole("ADMIN")
                    .requestMatchers("/api/category/**").hasRole("ADMIN")
                    .requestMatchers("/api/branch-stock/**").hasRole("STOCK_MANAGER")
                    .requestMatchers("/api/sale/**").hasRole("SELLER")
                    .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }


/*    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("2003"));
    }
    */
}