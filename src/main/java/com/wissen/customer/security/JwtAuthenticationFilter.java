package com.wissen.customer.security;

import com.wissen.customer.entities.Customer;
import com.wissen.customer.services.CustomerService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomerService customerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        logger.info(" Header :  {}", requestHeader);
        String phoneNumber = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
            logger.info(" Correct Header Token: {} ", token);
            try {
                phoneNumber = this.jwtHelper.getUsernameFromToken(token);
                logger.info(" Phone number: {} ", phoneNumber);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                logger.error(e.getMessage());
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                logger.error(e.getMessage());
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                logger.error(e.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else
            logger.info("Invalid Header Value !! ");

        if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Customer customerDetails = this.customerService.loadUserByPhoneNumber(phoneNumber);
            logger.info(customerDetails.getUsername());
            Boolean validateToken = this.jwtHelper.validateToken(token, customerDetails);
            if (validateToken) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customerDetails, null, customerDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else
                logger.info("Validation fails !!");
        }
        filterChain.doFilter(request, response);
    }
}
