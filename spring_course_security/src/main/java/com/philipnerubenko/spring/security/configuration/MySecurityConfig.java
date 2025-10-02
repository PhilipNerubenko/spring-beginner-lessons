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

/**
 * Spring Security configuration class that defines authentication and authorization rules.
 * Configures security settings including user details service, password encoding,
 * and HTTP request authorization policies.
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig {
  /**
   * Data source for database-backed user authentication.
   * Automatically injected by Spring.
   */
  @Autowired DataSource dataSource;

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

  /**
   * Configures a UserDetailsService implementation that retrieves user details from the database.
   * Uses JdbcUserDetailsManager with the configured DataSource.
   *
   * @return a UserDetailsService instance for database-based authentication
   */
  @Bean
  public UserDetailsService users() {
    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
    return manager;
  }

  /**
   * Creates a PasswordEncoder that delegates to multiple encoder types.
   * Supports legacy {noop} (no operation) passwords while allowing migration to stronger encoders.
   *
   * @return a configured PasswordEncoder instance
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /**
   * Configures the security filter chain for HTTP requests.
   * <p>
   * Access rules:
   * - Root endpoint ("/") requires any of MANAGER, HR, or EMPLOYEE roles
   * - "/hr_info" requires HR role
   * - "/manager_info/**" requires MANAGER role
   * - All other requests require authentication
   * <p>
   * Enables form-based login with default settings.
   *
   * @param http the HttpSecurity object to configure
   * @return the configured SecurityFilterChain
   * @throws Exception if configuration fails
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth
            -> auth.requestMatchers("/")
                   .hasAnyRole("MANAGER", "HR", "EMPLOYEE")
                   .requestMatchers("/hr_info")
                   .hasRole("HR")
                   .requestMatchers("/manager_info/**")
                   .hasRole("MANAGER")
                   .anyRequest()
                   .authenticated())
        .formLogin(Customizer.withDefaults());
    return http.build();
  }
}
