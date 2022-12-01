package com.example.demomapstruct.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class UpdatePostDto {
    private String title;
    private String content;
}
