package com.example.reservationservice.reservation.adapter.out.persistence;

import com.example.reservationservice.reservation.adapter.out.persistence.entity.mapper.TempReservationEntityMapper;
import com.example.reservationservice.reservation.adapter.out.persistence.repository.jpa.ReservationJpaRepository;
import com.example.reservationservice.reservation.application.port.out.GetTempReservationPort;
import com.example.reservationservice.reservation.application.port.out.SaveTempReservationPort;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import com.example.reservationservice.reservation.domain.TempReservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TempReservationPersistenceAdapter implements GetTempReservationPort, SaveTempReservationPort {
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Optional<TempReservation> find(TempReservation tempReservation) {
        return reservationJpaRepository
            .findByUserIdAndRoundIdAndStatusIs(tempReservation.userId(), tempReservation.roundId(), ReservationStatus.TEMP)
            .map(TempReservationEntityMapper::mapToModel);
    }

    @Override
    public TempReservation get(long id, long userId) {
        return reservationJpaRepository
            .findByIdAndUserIdAndStatusIs(id, userId, ReservationStatus.TEMP)
            .map(TempReservationEntityMapper::mapToModel)
            .orElseThrow();
    }
    
    @Override
    public void save(TempReservation tempReservation) {
        reservationJpaRepository.save(TempReservationEntityMapper.mapToEntity(tempReservation));
    }
}
