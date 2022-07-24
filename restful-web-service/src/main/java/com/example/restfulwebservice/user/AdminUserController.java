package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin") // 클래스 블록에 선언하여 클래스들 api의 prefix처럼 사용됨
public class AdminUserController {
    private UserDaoService service; // 인스턴스 선언

    //생성자를 통한 의존성 주입 (@Autowired를 사용할 수도 이뜸)
    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        /*JsonIgnore, JsonIgnoreProperties 대신 사용하는 필터링 방법*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn"); // 가져올 값 선택 가능
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users); // user를 MappingJacksonValue로 변환
        mapping.setFilters(filters);

        return mapping;
        //return service.findAll();
    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        /*JsonIgnore, JsonIgnoreProperties 대신 사용하는 필터링 방법*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "password", "ssn"); // 가져올 값 선택 가능
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user); // user를 MappingJacksonValue로 변환
        mapping.setFilters(filters);

        return mapping;
    }

    @PutMapping("/users/{id}")
    public void updateUser (@PathVariable int id,
                            @RequestBody User user) {
        User preUser = service.findOne(id);

        if (preUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        } else {
            preUser.setName(user.getName());
        }
    }

}
