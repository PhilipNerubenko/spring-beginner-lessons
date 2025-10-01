package com.philipnerubenko.spring.security.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    
    @Autowired
    DataSource dataSource;
    
// This user in memory
//    @Bean
//    public UserDetailsService users() {
//        UserDetails philip = User.builder()
//                .username("philip")
//                .password("{noop}philip")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails elena = User.builder()
//                .username("elena")
//                .password("{noop}elena")
//                .roles("HR")
//                .build();
//
//        UserDetails ivan = User.builder()
//                .username("ivan")
//                .password("{noop}ivan")
//                .roles("MANAGER", "HR")
//                .build();
//
//        return new InMemoryUserDetailsManager(philip, elena, ivan);
//    }
    
    // This user in DataBase
    @Bean
    public UserDetailsService users() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // всё равно {noop} работает
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").hasAnyRole("MANAGER", "HR", "EMPLOYEE")
                .requestMatchers("/hr_info").hasRole("HR")
                .requestMatchers("/manager_info/**").hasRole("MANAGER")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
