package com.example.reservationservice.tempreservation.adapter.out.persistence.repository.jpa;

import com.example.reservationservice.tempreservation.adapter.out.persistence.entity.TempReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempReservationJpaRepository extends JpaRepository<TempReservationEntity, Long> {
    Optional<TempReservationEntity> findByUserIdAndRoundId(Long userId, Long roundId);
}
