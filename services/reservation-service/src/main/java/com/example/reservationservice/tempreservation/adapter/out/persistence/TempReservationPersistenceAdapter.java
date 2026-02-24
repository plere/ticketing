package com.example.reservationservice.tempreservation.adapter.out.persistence;

import com.example.reservationservice.tempreservation.adapter.out.persistence.entity.mapper.TempReservationEntityMapper;
import com.example.reservationservice.tempreservation.adapter.out.persistence.repository.jpa.TempReservationJpaRepository;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import com.example.reservationservice.tempreservation.port.out.SaveTempReservationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TempReservationPersistenceAdapter implements GetTempReservationPort, SaveTempReservationPort {
    private final TempReservationJpaRepository tempReservationJpaRepository;
    
    @Override
    public Optional<TempReservation> find(TempReservation tempReservation) {
        return tempReservationJpaRepository
            .findByUserIdAndRoundId(tempReservation.userId(), tempReservation.roundId())
            .map(TempReservationEntityMapper::mapToModel);
    }

    @Override
    public void save(TempReservation tempReservation) {
        tempReservationJpaRepository.save(TempReservationEntityMapper.mapToEntity(tempReservation));
    }
}
