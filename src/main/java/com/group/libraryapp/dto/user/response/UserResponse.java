package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse {     // 유저 조회에 필요한 객체 (UserController에서 사용됨)

    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
