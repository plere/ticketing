package com.example.admin.concert.controller.dto;

import com.example.admin.concert.model.Concert;
import com.example.admin.concert.model.ConcertRound;
import com.example.admin.concert.model.dto.ModifyBasicConcertDto;
import com.example.admin.concert.service.validation.ValidationConcert;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

public record ModifyConcertBasicRequest(
    @NotBlank
    String name,

    String detailInfo,

    @NotNull
    @Positive
    Integer runningTime,

    @NotNull
    @Future
    LocalDateTime ticketingStartTime,

    @Future
    LocalDateTime openTime,

    @NotEmpty
    @Valid
    List<ModifyRoundRequest> rounds
) {

    public record ModifyRoundRequest(
        @NotNull
        @Future
        LocalDateTime startDateTime
    ) {
        public ConcertRound toModel(int sequenceNumber) {
            return ConcertRound.builder()
                .startDateTime(startDateTime)
                .sequenceNumber(sequenceNumber)
                .build();
        }
    }

    public ModifyBasicConcertDto toModifyDto() {
        List<ConcertRound> concertRounds = IntStream.range(0, rounds.size()).mapToObj(idx ->
            rounds.get(idx).toModel(idx + 1)
        ).toList();

        return ModifyBasicConcertDto.builder()
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .rounds(concertRounds)
            .build();
    }

    public ValidationConcert toValidationDto(Concert concert) {
        return ValidationConcert.builder()
            .id(concert.getId())
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .rounds(
                rounds.stream()
                    .map(round -> ValidationConcert.ValidationRound.builder()
                        .startDateTime(round.startDateTime)
                        .build())
                    .toList()
            )
            .build();
    }
}
