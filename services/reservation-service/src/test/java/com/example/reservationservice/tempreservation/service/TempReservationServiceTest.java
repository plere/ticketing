package com.example.reservationservice.tempreservation.service;

import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.HoldConcertSeatsPort;
import com.example.reservationservice.tempreservation.port.out.SaveTempReservationPort;
import com.example.reservationservice.tempreservation.service.exception.HoldSeatException;
import com.example.reservationservice.tempreservation.service.exception.TempReservationValidException;
import com.example.reservationservice.tempreservation.service.validation.TempReservationValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class TempReservationServiceTest {
    @InjectMocks
    private TempReservationService tempReservationService;
    @Mock
    private TempReservationValidation tempReservationValidation;
    @Mock
    private HoldConcertSeatsPort holdConcertSeatsPort;
    @Mock
    private SaveTempReservationPort saveTempReservationPort;

    @Test
    public void 정상적인_호출() {
        Set<Long> seatIds = Set.of(1L, 2L);
        TempReservation tempReservation = TempReservation.builder()
            .seatIds(seatIds)
            .build();
        tempReservationService.createAndHoldSeats(tempReservation);

        then(tempReservationValidation).should().validateTempReservation(tempReservation);
        then(holdConcertSeatsPort).should().holdSeats(seatIds);
        then(saveTempReservationPort).should().save(tempReservation);
    }


    @Test
    public void validation실패시_BadRequestException() {
        willThrow(TempReservationValidException.class)
            .given(tempReservationValidation).validateTempReservation(
                TempReservation.builder().build()
            );

        assertThatThrownBy(() -> tempReservationService.createAndHoldSeats(
            TempReservation.builder().build()
        ))
            .isInstanceOf(TempReservationValidException.class);
    }

    @Test
    public void 좌석선점시_실패시_save호출_안함() {
        willThrow(RuntimeException.class)
            .given(holdConcertSeatsPort)
            .holdSeats(any());

        try {
            tempReservationService.createAndHoldSeats(TempReservation.builder().build());
            fail("실패해야한다");
        } catch (Exception e) {
            then(saveTempReservationPort).should(never()).save(any());
        }
    }

    @Test
    public void 임시예매생성_실패시_release호출_및_에러발생() {
        willThrow(RuntimeException.class)
            .given(saveTempReservationPort)
            .save(any());

        assertThatThrownBy(() -> tempReservationService.createAndHoldSeats(TempReservation.builder().build()))
            .isInstanceOf(HoldSeatException.class);
        then(holdConcertSeatsPort).should().releaseSeats(any());
    }
}