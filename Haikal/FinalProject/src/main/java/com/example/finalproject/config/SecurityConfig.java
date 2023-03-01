package com.example.finalproject.config;

import com.example.finalproject.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/user/register/**", "/api/v1/user/logout").permitAll()
//                .requestMatchers( "/api/v1/customer", "/api/v1/customer/**").hasAuthority("customer")
                .requestMatchers("/api/v1/admin", "/api/v1/admin/**").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
//.requestMatchers(HttpMethod.POST, "/api/v1/store/**").hasAuthority("store")
//                .requestMatchers("/api/v1/admin/**", "/api/v1/admin/all-users").hasAuthority("ADMIN")

//.requestMatchers( "/api/v1/customer", "/api/v1/customer/**").hasAuthority("customer")
//                .requestMatchers("/api/v1/admin/**").hasAuthority("admin")
