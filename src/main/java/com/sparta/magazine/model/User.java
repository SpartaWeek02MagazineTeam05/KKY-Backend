package com.sparta.magazine.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="userId")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public User(String username, String password, String nickName, boolean activated) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.activated = activated;
    }

}
