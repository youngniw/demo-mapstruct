package com.example.demo.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
    private String name;
    private String loginId;
    private String loginPw;
}
