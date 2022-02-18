package com.sparta.magazine.service;

import com.sparta.magazine.dto.LoginRequestDto;
import com.sparta.magazine.dto.UserRequestDto;
import com.sparta.magazine.model.User;
import com.sparta.magazine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRequestDto requestDto) {
        User user = new User(requestDto.getUserId(), requestDto.getUserPwd(), requestDto.getNickName());
        userRepository.save(user);
    }

    public boolean login(LoginRequestDto requestDto) {
        Optional<User> user = userRepository.findByUserId(requestDto.getUserId());
        if (user.isPresent()) {
            if (user.get().getUserPwd().equals(requestDto.getUserPwd())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
