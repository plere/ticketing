package com.example.reservationservice.reservation.adapter.out.persistence.entity;

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
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Builder
@Getter
@Entity
@Table(name = "temp_reservation")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class TempReservationEntity {
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

    @Column(nullable = false)
    @Comment("user pk id")
    private Long userId;

    @Column(nullable = false)
    @Comment("concert pk id")
    private Long concertId;

    @Column(nullable = false)
    @Comment("해당 콘서트 회차 정보")
    private Long roundId;

    @Comment("")
    //how to save list to db
    private Set<Long> seatIds;
}
