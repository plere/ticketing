package com.example.concertservice.place.adapter.out.persistence.entity;

import com.example.concertservice.place.domain.PlaceSeat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "place")
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Comment("장소명")
    private String name;

    @Comment("주소")
    private String address;

    @Comment("총 좌석 수")
    private int totalSeatsCount;

    @OneToMany(mappedBy = "place", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PlaceSeatEntity> seats = new ArrayList<>();

    @Builder
    public PlaceEntity(Long id, String name, String address, int totalSeatsCount, List<PlaceSeat> seats) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.totalSeatsCount = totalSeatsCount;
        this.seats.addAll(seats.stream().map(PlaceSeatEntity::from).toList());
        this.seats.forEach(seat -> {
            if (seat.getPlace() != this) {
                seat.setPlace(this);
            }
        });
    }
}
