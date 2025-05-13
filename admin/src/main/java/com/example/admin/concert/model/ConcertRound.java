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
public class ConcertRound {
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
    private Concert concert;

    @Builder
    public ConcertRound(LocalDateTime startDateTime, Integer sequenceNumber) {
        this.startDateTime = startDateTime;
        this.sequenceNumber = sequenceNumber;
    }
}
