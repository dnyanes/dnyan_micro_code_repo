package com.ds.user_service.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse {

    private String message;
    private boolean status;
    private HttpStatus httpStatus;
}
