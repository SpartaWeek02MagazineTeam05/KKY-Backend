package com.sparta.magazine.repository;

import com.sparta.magazine.dto.LoginResponseDto;
import com.sparta.magazine.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAt();
}
