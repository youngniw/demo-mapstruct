package com.example.demomapstruct.dto;

import com.example.demomapstruct.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class PostDto {
    private Long pid;
    private User writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
}
