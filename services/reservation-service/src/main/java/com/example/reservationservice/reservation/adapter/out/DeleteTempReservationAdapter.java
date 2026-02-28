package com.example.reservationservice.reservation.adapter.out;

import com.example.reservationservice.reservation.adapter.out.persistence.entity.mapper.TempReservationEntityMapper;
import com.example.reservationservice.reservation.adapter.out.persistence.repository.jpa.ReservationJpaRepository;
import com.example.reservationservice.reservation.application.port.out.DeleteTempReservationPort;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import com.example.reservationservice.reservation.domain.TempReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteTempReservationAdapter implements DeleteTempReservationPort {
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public List<TempReservation> getOldTempReservationsBefore(int seconds) {
        return reservationJpaRepository
            .findAllByCreatedAtBeforeAndStatusIs(LocalDateTime.now().minusSeconds(seconds), ReservationStatus.TEMP)
            .stream()
            .map(TempReservationEntityMapper::mapToModel)
            .toList();
    }

    @Override
    public void deleteOldTempReservations(List<Long> oldTempReservationIds) {
        reservationJpaRepository.deleteAllByIdInAndStatusIs(oldTempReservationIds, ReservationStatus.TEMP);
    }
}
