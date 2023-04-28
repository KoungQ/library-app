package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// V2: Spring Data JPA로 기능 구현
@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래에 있는 함수가 시작될 때 start transaction을 해줌(트랜젝션 시작)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request) {
                    // save: 주어지는 객체를 저장하거나 업데이트 시켜줌
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true) // 조회할 때는 읽기 전용으로 해두면 성능이 조금 더 좋아짐
    public List<UserResponse> getUsers() {
        // findAll: 주어지는 객체가 매핑된 테이블의 모든 데이터를 가져옴
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
                                // findById: id를 기준으로 특정한 1개의 데이터를 가져옴
        User user = userRepository.findById(request.getId())
                    .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
                                // findByName == SELECT * FROM user WHERE name = ?;
        User user = userRepository.findByName(name)
                        .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
