package com.sparta.magazine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LikeNumber {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "likeId")
    private Long id;

    @Column
    private String postId;

    @Column
    private String userId;

    @ManyToOne
    @JoinColumn(name = "USER_ACCOUNTID", nullable = false)
    private User users;

    @ManyToOne
    @JoinColumn(name = "POST_POSTID", nullable = false)
    private Post posts;

    public LikeNumber(String postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
