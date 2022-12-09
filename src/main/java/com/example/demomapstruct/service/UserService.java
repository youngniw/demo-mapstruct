package com.example.demomapstruct.service;

import com.example.demomapstruct.dto.UserDto;
import com.example.demomapstruct.dto.SaveUserDto;
import com.example.demomapstruct.entity.User;
import com.example.demomapstruct.mapper.SignUpUserMapper;
import com.example.demomapstruct.mapper.UserMapper;
import com.example.demomapstruct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SignUpUserMapper signUpUserMapper;
    private final UserMapper userMapper;

    @Transactional
    public User saveUser(SaveUserDto saveUserDto) {
        userRepository.findByLoginId(saveUserDto.getLoginId())
                .ifPresent(user -> {
                    throw new RuntimeException("이미 존재하는 아이디입니다.");
                });

        User user = signUpUserMapper.toEntity(saveUserDto);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> getUser(final Long uid) {
        return userRepository.findById(uid)
                .map(userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUserList() {
        List<User> userList = userRepository.findAll();

        return userMapper.toDto(userList);
    }
}
