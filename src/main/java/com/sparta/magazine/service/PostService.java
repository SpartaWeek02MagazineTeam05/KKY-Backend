package com.sparta.magazine.service;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.dto.UserResponseDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPost() {
        List<Post> list = postRepository.findAllByOrderByModifiedAt();
        return list;
    }

    public UserResponseDto posting(PostRequestDto requestDto) {
        UserResponseDto responseDto = new UserResponseDto();
        Post post = new Post(requestDto);
        if (post.getContent().equals("")) {
            responseDto.setResult(false);
            responseDto.setMsg("포스팅에 실패했습니다. 내용을 채워주세요.");
            return responseDto;
        } else {
            postRepository.save(post);
            responseDto.setResult(true);
            responseDto.setMsg("포스팅이 완료되었습니다.");
            return responseDto;
        }
    }

    public void deletepost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public UserResponseDto updatepost(Long postId, PostRequestDto requestDto) {
        UserResponseDto responseDto = new UserResponseDto();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("이미 삭제된 글은 삭제할수 없습니다."));

        post.update(requestDto);
        responseDto.setResult(true);
        responseDto.setMsg("수정이 완료되었습니다.");
        return responseDto;
    }
}
