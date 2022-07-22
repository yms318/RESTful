package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

//공통은 AOP기능을 사용한다
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
