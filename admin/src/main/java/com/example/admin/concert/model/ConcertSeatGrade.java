package com.example.admin.concert.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ConcertSeatGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Comment("등급명")
    private String name;

    @Comment("가격")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @Comment("콘서트정보")
    private Concert concert;

    @Builder
    public ConcertSeatGrade(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
