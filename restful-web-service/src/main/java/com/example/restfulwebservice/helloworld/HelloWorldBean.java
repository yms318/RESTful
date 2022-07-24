package com.example.restfulwebservice.helloworld;
//lombok

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Annotation Processors > Enable annotation processing 체크해야함
@AllArgsConstructor // 모든 argument를 가지고있는 constructor 생성
@NoArgsConstructor // 매개변수가 없는 Default 생성자
public class HelloWorldBean {
    private String message;

    // public HelloWorldBean(String message) {
    //  this.message = message;
    // }
}
