package com.example.concertservice.concert.adapter.out.persistence.entity;

import com.example.concertservice.concert.domain.ConcertSeatState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@Table(name = "concert_seat")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ConcertSeatEntity {
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
    @Comment("행 순서")
    private Integer rowOrder;

    @Column(nullable = false)
    @Comment("층")
    private int floor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("좌석 상태")
    private ConcertSeatState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("콘서트 정보")
    @Setter
    private ConcertEntity concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("좌석 등급")
    private ConcertSeatGradeEntity grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("콘서트 회차 정보")
    private ConcertRoundEntity round;

}
