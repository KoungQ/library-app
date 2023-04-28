package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {    // 유저 생성 기능에 필요한 객체 (UserController에서 사용)

    private String name;
    private Integer age;    // int = null(x), Integer = null(o)

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}