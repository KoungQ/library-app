package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity // 스프링이 User 객체와 User 테이블을 같은 것으로 바라봄
public class User {

    @Id // 이 필드를 PK로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK를 자동 생성함 == AUTO INCREMENT
    private Long id = null; // bigint == long

    @Column(nullable = false, length = 20, name = "name") // == name varchar(20)
    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // 1(사용자) : N(사용자 대출기록) 관계
    private List<UserLoanHistory> userLoanHistory = new ArrayList<>();

    protected User() {}

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        this.userLoanHistory.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistory.stream()
                .filter(history -> history.getBook().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
