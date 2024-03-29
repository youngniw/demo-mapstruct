package com.example.demomapstruct.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
    private Long uid;
    private String name;
    private Boolean isCreatedToday;
}
