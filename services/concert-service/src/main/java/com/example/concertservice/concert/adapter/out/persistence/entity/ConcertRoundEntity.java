package com.example.concertservice.concert.adapter.out.persistence.entity;

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
@Table(name = "concert_round")
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ConcertRoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Comment("공연일")
    private LocalDateTime startDateTime;

    @Comment("회차 순서")
    private Integer sequenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @Comment("콘서트 정보")
    private ConcertEntity concert;

    @Builder
    public ConcertRoundEntity(Long id, LocalDateTime startDateTime, Integer sequenceNumber) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.sequenceNumber = sequenceNumber;
    }
}
