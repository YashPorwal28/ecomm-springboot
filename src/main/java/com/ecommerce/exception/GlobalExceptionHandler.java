package com.ecommerce.exception;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(name = "userGlobalExceptionHandler")
public class GlobalExceptionHandler {

    private static ResponseEntity<ErrorResponse> getErrorResponseEntity (Throwable ex , WebRequest req, HttpStatus httpStatus){
             ErrorResponse errorResponse = new ErrorResponse(new Date(), httpStatus.toString(), ex.getMessage(), req.getDescription(false) );
             return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler({BadCredentialsException.class,AuthorizationDeniedException.class, AccessDeniedException.class})
    public ResponseEntity<?> badCredentials ( BadCredentialsException ex , WebRequest req){
        return getErrorResponseEntity(ex, req, HttpStatus.FORBIDDEN);
    }
}
