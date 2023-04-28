package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Service에서 스프링 빈으로 등록해서 jdbcTemplate이 필요 없음
@RestController
public class UserController {

    // Controller -> Service -> Repository

    // 스프링  빈 주입 받는 방법 3가지
    // 1. 생성자 사용 (@Autowired 생략 가능): 가장 선호
    private final UserServiceV2 userService;
    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    // 2. setter + @Autowired: 누군가 setter를 사용하면 오작동 가능성 O
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    // 3. 필드에 바로 사용: 테스트를 어렵게 만드는 요인
//    @Autowired
//    private UserService userService;


    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
       userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}