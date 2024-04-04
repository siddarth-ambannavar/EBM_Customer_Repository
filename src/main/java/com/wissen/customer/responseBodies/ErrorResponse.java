package com.wissen.customer.responseBodies;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String errorMessage;
    private final boolean success = false;
    private HttpStatus status;
}
