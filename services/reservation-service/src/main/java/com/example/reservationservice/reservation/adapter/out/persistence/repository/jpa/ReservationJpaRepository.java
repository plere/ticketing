package com.example.reservationservice.reservation.adapter.out.persistence.repository.jpa;

import com.example.reservationservice.reservation.adapter.out.persistence.entity.ReservationEntity;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findByUserIdAndRoundIdAndStatusIs(Long userId, Long roundId, ReservationStatus status);

    List<ReservationEntity> findAllByCreatedAtBeforeAndStatusIs(LocalDateTime createdAtBefore, ReservationStatus status);

    void deleteAllByIdInAndStatusIs(List<Long> ids, ReservationStatus status);
}
