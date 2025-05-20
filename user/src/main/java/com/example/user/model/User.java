package com.example.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Comment("수정일")
    private LocalDateTime updatedAt;

    @Column(unique = true, nullable = false)
    @Comment("이메일, 로그인 아이디")
    private String email;

    @Column(nullable = false)
    @Comment("이름")
    private String name;

    @Column(nullable = false)
    @Comment("비밀번호")
    private String password;

    @Comment("주소")
    private String address;

    @Comment("핸드폰번호")
    private String phone;
}
