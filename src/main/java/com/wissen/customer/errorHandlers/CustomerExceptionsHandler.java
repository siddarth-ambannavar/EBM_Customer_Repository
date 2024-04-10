package com.wissen.customer.errorHandlers;

import com.wissen.customer.customExceptions.CustomerAlreadyExistsException;
import com.wissen.customer.customExceptions.InValidLoginCredentialsException;
import com.wissen.customer.customExceptions.InValidSignInCredentialsException;
import com.wissen.customer.customExceptions.UnAuthenticatedAccessException;
import com.wissen.customer.reqResModels.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionsHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleInValidLoginCredentialsException(
            InValidLoginCredentialsException exception
    ) {
        String errorMsg = exception.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .errorMessage(errorMsg)
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InValidSignInCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInValidSignInCredentialsException(
            InValidSignInCredentialsException exception
    ) {
        String errorMsg = exception.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .errorMessage(errorMsg)
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException exception
    ) {
        String errorMsg = exception.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .errorMessage(errorMsg)
                .status(HttpStatus.CONFLICT)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnAuthenticatedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthenticatedAccessException(
            UnAuthenticatedAccessException exception
    ) {
        String errorMsg = exception.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .errorMessage(errorMsg)
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
