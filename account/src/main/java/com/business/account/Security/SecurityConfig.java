package com.business.account.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("") // custom login page URL
                        .loginProcessingUrl("") // URL where the login form submits (usually /login)
                        .defaultSuccessUrl("") // Redirect after successful login
                        .permitAll() // Important: Allow access to the login page itself!
                )
                .logout((logout) -> logout
                        .permitAll()); // Allow everyone to logout

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }


//    private static final String ADMIN_PATH = "/admin/**";
//    private static final String USER_PATH = "/Registration/**";
//    private static final String LOGIN_PAGE = "/Login";
//    private static final String ROOT_PATH = "/";
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        configureAuthorization(http);
//        http
//                .loginPage(LOGIN_PAGE)
//                .permitAll()
//                .logout()
//                .permitAll();
//
//        return http.build();
//    }
//
//    private void configureAuthorization(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authzConfig -> {
//            authzConfig
//                    .dispatcherTypeMatchers(HttpMethod.valueOf(ADMIN_PATH)).hasRole("ADMIN")
//                    .dispatcherTypeMatchers(HttpMethod.valueOf(USER_PATH)).hasAnyRole("USER", "ADMIN")
//                    .dispatcherTypeMatchers(HttpMethod.valueOf(ROOT_PATH)).permitAll()
//                    .anyRequest().authenticated();
//        });
//    }


    // In-memory user details (for testing - replace with a database or other user details service)
    // You'll likely want to remove this and use a proper user details service
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    */
}