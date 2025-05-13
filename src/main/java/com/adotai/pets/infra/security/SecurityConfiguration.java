package com.adotai.pets.infra.security;

import com.adotai.pets.infra.security.filters.OrganizationAuthenticationFilter;
import com.adotai.pets.infra.security.filters.UserAuthenticationFilter;
import com.adotai.pets.infra.security.handlers.CustomAccessDeniedHandler;
import com.adotai.pets.infra.security.handlers.CustomAuthEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;
    private final OrganizationAuthenticationFilter organizationAuthenticationFilter;
    private final CustomCorsConfiguration customCorsConfiguration;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthEntryPoint customAuthEntryPoint;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/user/login",
            "/user/register",
            "/organization/register",
            "/organization/login"
    };

    public static final String [] ENDPOINTS_WITH_USER_AUTHENTICATION_REQUIRED = {
            "/user/testauth"
    };

    public static final String [] ENDPOINTS_WITH_ORGANIZATION_AUTHENTICATION_REQUIRED = {
            "/organization/testauth",
            "/pet",
            "/pet/register"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(customCorsConfiguration))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers(ENDPOINTS_WITH_USER_AUTHENTICATION_REQUIRED).hasRole("USER")
                            .requestMatchers(ENDPOINTS_WITH_ORGANIZATION_AUTHENTICATION_REQUIRED).hasRole("ORGANIZATION")
                            .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(organizationAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAuthEntryPoint))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
