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
    public void save(SaveUserDto saveUserDto) {
        userRepository.save(signUpUserMapper.toEntity(saveUserDto));
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
