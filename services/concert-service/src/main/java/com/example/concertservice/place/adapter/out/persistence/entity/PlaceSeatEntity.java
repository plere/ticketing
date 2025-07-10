package com.example.concertservice.place.adapter.out.persistence.entity;

import com.example.concertservice.place.domain.PlaceSeat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "place_seat")
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PlaceSeatEntity {
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
    private PlaceEntity place;

    public static PlaceSeatEntity from(PlaceSeat domain) {
        return PlaceSeatEntity.builder()
            .id(domain.getId())
            .floor(domain.getFloor())
            .rowOrder(domain.getRowOrder())
            .rowName(domain.getRowName())
            .rowCount(domain.getRowCount())
            .build();
    }
}
