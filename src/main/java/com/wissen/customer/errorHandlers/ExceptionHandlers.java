package com.wissen.customer.errorHandlers;

import com.wissen.customer.exceptions.CustomerAlreadyExistsException;
import com.wissen.customer.exceptions.CustomerNotFoundException;
import com.wissen.customer.exceptions.InvalidSignUpCredentialsException;
import com.wissen.customer.responseBodies.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException exception
    ) {
        String message = exception.getMessage();
        ErrorResponse error = ErrorResponse.builder()
                .errorMessage(message)
                .status(HttpStatus.CONFLICT)
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidSignUpCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSignUpCredentialsException(
            InvalidSignUpCredentialsException exception
    ) {
        String message = exception.getMessage();
        ErrorResponse error = ErrorResponse.builder()
                .errorMessage(message)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
            CustomerNotFoundException exception
    ) {
        String message = exception.getMessage();
        ErrorResponse error = ErrorResponse.builder()
                .errorMessage(message)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
