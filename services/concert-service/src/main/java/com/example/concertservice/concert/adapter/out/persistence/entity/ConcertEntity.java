package com.example.concertservice.concert.adapter.out.persistence.entity;

import com.example.concertservice.concert.domain.ConcertState;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "concert")
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ConcertEntity {
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

    @Comment("콘서트명")
    private String name;

    @Comment("상세정보")
    private String detailInfo;

    @Comment("러닝타임")
    private int runningTime;

    @Enumerated(EnumType.STRING)
    @Comment("상태")
    @Setter
    private ConcertState state;

    @Comment("티케팅 시작일")
    private LocalDateTime ticketingStartTime;

    @Comment("유저 화면에 노출되는 시점")
    private LocalDateTime openTime;

    @Comment("장소 pk id")
    private Long placeId;

    @Comment("장소명")
    private String placeName;

    @OneToMany(mappedBy = "concert", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("회차정보")
    private final List<ConcertRoundEntity> rounds = new ArrayList<>();

    @OneToMany(mappedBy = "concert", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("좌석 등급 정보")
    private final List<ConcertSeatGradeEntity> seatGrades = new ArrayList<>();

    @OneToMany(mappedBy = "concert", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("좌석 정보")
    private final List<ConcertSeatEntity> seats = new ArrayList<>();

    @Builder
    public ConcertEntity(Long id, String name, String detailInfo, int runningTime, ConcertState state,
                         LocalDateTime ticketingStartTime, LocalDateTime openTime, Long placeId, String placeName,
                         List<ConcertRoundEntity> rounds, List<ConcertSeatGradeEntity> seatGrades,
                         List<ConcertSeatEntity> seats) {
        this.id = id;
        this.name = name;
        this.detailInfo = detailInfo;
        this.runningTime = runningTime;
        this.state = state;
        this.ticketingStartTime = ticketingStartTime;
        this.openTime = openTime;
        this.placeId = placeId;
        this.placeName = placeName;
        this.rounds.addAll(rounds);
        this.seatGrades.addAll(seatGrades);
        this.seats.addAll(seats);

        this.rounds.forEach(round -> {
            if (round.getConcert() != this) {
                round.setConcert(this);
            }
        });

        this.seatGrades.forEach(seatGrade -> {
            if (seatGrade.getConcert() != this) {
                seatGrade.setConcert(this);
            }
        });

        this.seats.forEach(seat -> {
            if (seat.getConcert() != this) {
                seat.setConcert(this);
            }
        });
    }
}
