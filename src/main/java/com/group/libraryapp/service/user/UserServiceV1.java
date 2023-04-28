package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// V1: SQL 쿼리문을 직접 작성하여 기능 구현
@Service    // 스프링 빈 등록
public class UserServiceV1 {

    // Repository에서 스프링 빈으로 등록해서 jdbcTemplate이 필요 없음
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    // C(reate)
    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    // R(ead)
    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

    // U(pdate)
    public void updateUser(UserUpdateRequest request) {
        // 존재하지 않는 사용자에 대해 수정하려 할 때
        if(userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    // D(elete)
    public void deleteUser(String name) {
        // 존재하지 않는 사용자에 대해 삭제하려 할 때
        if(userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

       userJdbcRepository.deleteUser(name);
    }
}
