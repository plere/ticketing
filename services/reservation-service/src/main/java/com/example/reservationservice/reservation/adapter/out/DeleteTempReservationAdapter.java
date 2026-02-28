package com.example.reservationservice.reservation.adapter.out;

import com.example.reservationservice.reservation.adapter.out.persistence.entity.mapper.TempReservationEntityMapper;
import com.example.reservationservice.reservation.adapter.out.persistence.repository.jpa.TempReservationJpaRepository;
import com.example.reservationservice.reservation.application.port.out.DeleteTempReservationPort;
import com.example.reservationservice.reservation.domain.TempReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteTempReservationAdapter implements DeleteTempReservationPort {
    private final TempReservationJpaRepository tempReservationJpaRepository;

    @Override
    public List<TempReservation> getOldTempReservationsBefore(int seconds) {
        return tempReservationJpaRepository.findAllByCreatedAtBefore(LocalDateTime.now().minusSeconds(seconds))
            .stream()
            .map(TempReservationEntityMapper::mapToModel)
            .toList();
    }

    @Override
    public void deleteOldTempReservations(List<Long> oldTempReservationIds) {
        tempReservationJpaRepository.deleteAllById(oldTempReservationIds);
    }
}
