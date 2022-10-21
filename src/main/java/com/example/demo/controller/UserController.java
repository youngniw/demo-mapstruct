package com.example.demo.controller;

import com.example.demo.dto.UserDataDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/user/list")
    public ResponseEntity<UserDataDto> getUserList() {
        UserDataDto userList = userService.getUserList();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
