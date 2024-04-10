package com.wissen.customer.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissen.customer.reqResModels.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ErrorResponse error = ErrorResponse.builder()
                        .errorMessage("Invalid Phone Number or Password")
                        .status(HttpStatus.UNAUTHORIZED)
                        .build();
        ResponseEntity<ErrorResponse> res = new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        objectMapper.writeValue(response.getWriter(), res.getBody());
    }
}