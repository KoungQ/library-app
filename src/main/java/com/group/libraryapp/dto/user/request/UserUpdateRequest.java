package com.group.libraryapp.dto.user.request;

public class UserUpdateRequest {    // 유저 업데이트 기능에 필요한 객체 (UserController에서 사용)

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
