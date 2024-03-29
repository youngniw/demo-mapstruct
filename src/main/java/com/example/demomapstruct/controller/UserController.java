package com.example.demomapstruct.controller;

import com.example.demomapstruct.dto.UserDto;
import com.example.demomapstruct.dto.SaveUserDto;
import com.example.demomapstruct.entity.User;
import com.example.demomapstruct.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 정보 추가
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SaveUserDto saveUserDto) {
        User user = userService.saveUser(saveUserDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{pid}")
                .buildAndExpand(user.getUid())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    // uid에 해당하는 회원 정보 조회
    @GetMapping("/user/{uid}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable("uid") Long uid) {
        Optional<UserDto> user = userService.getUser(uid);

        return user
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    }

    // 회원 목록 조회
    @GetMapping("/user/list")
    public ResponseEntity<List<UserDto>> getUserList() {
        List<UserDto> userList = userService.getUserList();

        return ResponseEntity.ok(userList);
    }
}
