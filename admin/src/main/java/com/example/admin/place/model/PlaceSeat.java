package com.example.admin.place.model;

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
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PlaceSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Comment("층 정보")
    private Integer floor;

    @Comment("행 순서")
    private Integer rowOrder;

    @Comment("행 이름")
    private String rowName;

    @Comment("한행에 속한 좌석 수")
    private Integer rowCount;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("장소 정보")
    private Place place;
}
