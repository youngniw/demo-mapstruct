package com.example.demomapstruct.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDataDto {
    private String name;
    private String loginId;
    private String loginPw;
    private LocalDateTime createdDate;
    private Boolean isCreatedToday;
}
