package com.sparta.magazine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "postId")
    private Long id;

    @Column
    private String content;

    @Column
    private Long likeNumber;

    @Column
    private String nickName;

    @Column
    private String image;

    public Post(String content, Long likeNumber, String nickName, String image) {
        this.content = content;
        this.likeNumber = likeNumber;
        this.nickName = nickName;
        this.image = image;
    }

}
