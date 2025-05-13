package com.adotai.pets.infra.security.filters;

import com.adotai.pets.core.domain.User;
import com.adotai.pets.core.exceptions.InvalidOrMissingAuthTokenException;
import com.adotai.pets.core.gateways.UserGateway;
import com.adotai.pets.infra.security.JwtService;
import com.adotai.pets.infra.security.SecurityConfiguration;
import com.adotai.pets.infra.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserGateway userGateway;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, InvalidOrMissingAuthTokenException, IOException {
        if (checkIfEndpointIsNotPublic(request) && checkIfEndpointIsForUser(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                String subject = jwtService.getSubjectFromToken(token);

                User user = userGateway.findUserByEmail(subject).orElseThrow();

                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                var authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new InvalidOrMissingAuthTokenException("Token is missing");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }

    private boolean checkIfEndpointIsForUser(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_USER_AUTHENTICATION_REQUIRED).contains(requestURI);
    }
}
