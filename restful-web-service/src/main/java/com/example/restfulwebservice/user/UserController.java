package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service; // 인스턴스 선언

    //생성자를 통한 의존성 주입 (@Autowired를 사용할 수도 이뜸)
    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    // form 데이터 타입 아닌 json, xml같은 object 타입을 선언할 시 @RequestBody 사용
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        //응답코드 제어 (fromCurrentRequest : 현재 요청된 값을 사용)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri(); //가변변수에 savedUser의 id값을 넣어 uri 형태로 변경

        return ResponseEntity.created(location).build(); //저장된 User의 주소값을 반환
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
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
