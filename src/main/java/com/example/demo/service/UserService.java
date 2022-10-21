package com.example.demo.service;

import com.example.demo.dto.UserDataDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserDataMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
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

    public UserDataDto getUserList() {
        List<User> userList = userRepository.findAll();

        return userDataMapper.toDto(userList).get(0);
    }
}
