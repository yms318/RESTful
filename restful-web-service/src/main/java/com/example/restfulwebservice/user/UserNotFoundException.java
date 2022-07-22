package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status code
// 2XX -> OK
// 4XX -> Client
// 5XX -> Server

@ResponseStatus(HttpStatus.NOT_FOUND) // 5XX이 아닌 404 Not Found를 보여주도록 함
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
