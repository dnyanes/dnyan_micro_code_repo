package com.ds.user_service.exception;

import com.ds.user_service.payload.ApiResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<@NonNull ApiResponse> resourceNotFoundHandler(ResourceNotFoundException exception){

        ApiResponse response = ApiResponse.builder()
                .message(exception.getMessage())
                .status(true)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
