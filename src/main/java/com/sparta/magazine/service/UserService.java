package com.sparta.magazine.service;

import com.sparta.magazine.dto.UserRequestDto;
import com.sparta.magazine.dto.UserResponseDto;
import com.sparta.magazine.model.User;
import com.sparta.magazine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto register(UserRequestDto requestDto) {
        UserResponseDto responseDto = new UserResponseDto();
        boolean validateNickName = Pattern.matches("^[a-zA-Z0-9]{3,10}$", requestDto.getNickName());
        boolean validatePwd = Pattern.matches("^[a-zA-Z0-9]{4,13}$", requestDto.getPassword());

        if (validatePwd == false) {
            responseDto.setSuccessCheck(false);
            responseDto.setMessage("비밀번호는 최소 4자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 사용가능합니다.");
            return responseDto;
        } else if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())) {
            responseDto.setSuccessCheck(false);
            responseDto.setMessage("비밀번호가 일치하지 않습니다.");
            return responseDto;
        } else if (validateNickName == false || requestDto.getNickName().equals(requestDto.getPassword())) {
            responseDto.setSuccessCheck(false);
            responseDto.setMessage("닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 사용가능합니다.");
            return responseDto;
        } else {
            String password = passwordEncoder.encode(requestDto.getPassword());
            User user = new User(requestDto.getUsername(), password, requestDto.getNickName());
            userRepository.save(user);
            responseDto.setSuccessCheck(true);
            responseDto.setMessage("회원가입이 완료되었습니다..");
            return responseDto;
        }
    }

}
