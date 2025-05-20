package com.example.admin.concert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ConcertSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Comment("열번호")
    private int columnNum;

    @Column(nullable = false)
    @Comment("행")
    private String rowNum;

    @Column(nullable = false)
    @Comment("층")
    private int floor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("좌석 상태")
    private ConcertSeatState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("좌석 등급")
    private ConcertSeatGrade grade;

    @Column(nullable = false)
    private long concertId;
}
