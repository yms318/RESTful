package com.example.restfulwebservice.helloworld;
//lombok

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor //매개변수가 없는 Default 생성자
public class HelloWorldBean {
    private String message;
    //public HelloWorldBean(String message) { this.message = message; }
}
