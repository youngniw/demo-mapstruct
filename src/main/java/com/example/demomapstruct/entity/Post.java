package com.example.demomapstruct.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Table
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 키 생성을 데이터베이스에 위임
    private Long pid;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)   // referencedColumnName 속성: 엔티티의 속성명 "uid"
    private User writer;

    @Setter     // partialUpdate 가능
    @Column(length = 50, nullable = false)
    private String title;

    @Setter     // partialUpdate 가능
    @Column(length = 250, nullable = false)
    private String content;
}
