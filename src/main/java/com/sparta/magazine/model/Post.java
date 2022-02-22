package com.sparta.magazine.model;

import com.sparta.magazine.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false)
    private String content;

    @Column
    @ColumnDefault("0")
    private Long likeCount;

    @Column(nullable = false)
    private String nickName;

    @Column
    private String image;

    @Column(nullable = false)
    private String type;

    public Post(PostRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.nickName = requestDto.getNickName();
        this.image = requestDto.getImage();
        this.type = requestDto.getType();
    }

    public void update(PostRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.nickName = requestDto.getNickName();
        this.image = requestDto.getImage();
        this.type = requestDto.getImage();
    }

}
