package com.example.demomapstruct.service;

import com.example.demomapstruct.dto.UserDataDto;
import com.example.demomapstruct.dto.UserDto;
import com.example.demomapstruct.entity.User;
import com.example.demomapstruct.mapper.UserDataMapper;
import com.example.demomapstruct.mapper.UserMapper;
import com.example.demomapstruct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDataMapper userDataMapper;

    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        User saveUser = userRepository.save(user);
        return userMapper.toDto(saveUser);
    }

    public List<UserDataDto> getUserList() {
        List<User> userList = userRepository.findAll();

        return userDataMapper.toDto(userList);
    }
}
