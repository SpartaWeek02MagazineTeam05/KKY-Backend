package com.sparta.magazine.controller;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.dto.UserResponseDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/post/{userId}")
    @ResponseBody
    public List<Post> getPost(@PathVariable Long userId) {
        List<Post> list = postService.getPost();
        return list;
    }

    @PostMapping("/api/post")
    @ResponseBody
    public UserResponseDto posting(@RequestBody PostRequestDto requestDto) {
        return postService.posting(requestDto);
    }

    @DeleteMapping("/api/post/{postId}")
    @ResponseBody
    public UserResponseDto deletepost(@PathVariable Long postId) {
        postService.deletepost(postId);
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setResult(true);
        responseDto.setMsg("삭제가 완료되었습니다.");
        return responseDto;
    }

    @PutMapping("/api/post/{postId}")
    @ResponseBody
    public UserResponseDto updatepost (@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatepost(postId, requestDto);
    }
}
