package com.sparta.magazine.model;

import com.sparta.magazine.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="accountId")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false, unique = true)
    private String nickName;

    public User(String userId, String userPwd, String nickName) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.nickName = nickName;
    }

}
