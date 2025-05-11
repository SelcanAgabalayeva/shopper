package az.edu.itbrains.shopper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeHttpRequests((c) -> c
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .failureUrl("/login")
                        .failureForwardUrl("/login")


                )

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(((request, response, accessDeniedException) ->
                                response.sendRedirect("/login")
                        )))
                .logout((log) -> log.logoutSuccessUrl("/login"));


        return http.build();


    }

    public void configure(AuthenticationManagerBuilder auth)

            throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }
}