package com.example.reservationservice.tempreservation.application.scheduler;

import com.example.reservationservice.tempreservation.application.port.out.DeleteTempReservationPort;
import com.example.reservationservice.tempreservation.application.port.out.HoldConcertSeatsPort;
import com.example.reservationservice.tempreservation.domain.TempReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeleteTempReservationScheduler {
    private final DeleteTempReservationPort deleteTempReservationPort;
    private final HoldConcertSeatsPort holdConcertSeatsPort;

    @Scheduled(
        cron = "*/10 * * * * *"
    )
    @Transactional
    public void deleteOldTempReservations() {
        List<TempReservation> oldTempReservations = deleteTempReservationPort.getOldTempReservationsBefore(60 * 5);
        Set<Long> seatIds = oldTempReservations.stream().map(TempReservation::seatIds).flatMap(Set::stream).collect(Collectors.toSet());
        holdConcertSeatsPort.releaseSeats(seatIds);
        deleteTempReservationPort.deleteOldTempReservations(
            oldTempReservations.stream().map(TempReservation::id).toList()
        );
    }
}
