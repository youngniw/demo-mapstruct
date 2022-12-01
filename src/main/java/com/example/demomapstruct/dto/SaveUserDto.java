package com.example.demomapstruct.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveUserDto {
    private String name;
    private String loginId;
    private String loginPw;
}
