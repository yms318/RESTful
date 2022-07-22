package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value={"password"})
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 두글자 이상 입력해주세요")
    private String name;
    @Past //미래 데이터를 쓸 수 없다는 제약조건
    private Date joinDate;

    //@JsonIgnore
    private String password;
    private String ssn;
}
