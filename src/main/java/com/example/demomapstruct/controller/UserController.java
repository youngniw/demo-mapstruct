package com.example.demomapstruct.controller;

import com.example.demomapstruct.dto.UserDataDto;
import com.example.demomapstruct.dto.UserDto;
import com.example.demomapstruct.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<UserDataDto>> getUserList() {
        List<UserDataDto> userList = userService.getUserList();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
