package com.example.admin.concert.model;

import com.example.admin.concert.model.dto.ModifyBasicConcertDto;
import com.example.admin.concert.model.dto.ModifyConcertPlaceDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Concert {
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
    private ConcertState state;

    @Comment("티케팅 시작일")
    private LocalDateTime ticketingStartTime;

    @Comment("유저 화면에 노출되는 시점")
    private LocalDateTime openTime;

    @Comment("장소 pk id")
    private Long placeId;

    @OneToMany(mappedBy = "concert", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("회차정보")
    private final List<ConcertRound> rounds = new ArrayList<>();

    @OneToMany(mappedBy = "concert", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("좌석 등급 정보")
    private final List<ConcertSeatGrade> seatGrades = new ArrayList<>();

    @Builder
    public Concert(String name, String detailInfo, int runningTime, ConcertState state, LocalDateTime ticketingStartTime, LocalDateTime openTime, Long placeId, List<ConcertRound> rounds, List<ConcertSeatGrade> seatGrades) {
        this.name = name;
        this.detailInfo = detailInfo;
        this.runningTime = runningTime;
        this.state = state;
        this.ticketingStartTime = ticketingStartTime;
        this.openTime = openTime;
        this.placeId = placeId;
        this.rounds.addAll(rounds);
        this.seatGrades.addAll(seatGrades);

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
    }

    public void modifyBasic(ModifyBasicConcertDto dto) {
        this.name = dto.name();
        this.detailInfo = dto.detailInfo();
        this.runningTime = dto.runningTime();
        this.ticketingStartTime = dto.ticketingStartTime();
        this.openTime = dto.openTime();
        this.rounds.clear();
        this.rounds.addAll(dto.rounds());

        this.rounds.forEach(round -> {
            if (round.getConcert() != this) {
                round.setConcert(this);
            }
        });
    }

    public void modifyPlace(ModifyConcertPlaceDto dto) {
        this.placeId = dto.placeId();
        this.seatGrades.clear();
        this.seatGrades.addAll(dto.seatGrades());

        this.seatGrades.forEach(seatGrade -> {
            if (seatGrade.getConcert() != this) {
                seatGrade.setConcert(this);
            }
        });
    }

    public void updateStateToClose() {
        this.state = ConcertState.CLOSE;
    }
}
