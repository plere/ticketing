package com.example.concertservice.concert.adapter.in.web.request;

import com.example.concertservice.concert.application.port.in.ModifyBasicConcertCommand;
import com.example.concertservice.concert.domain.ConcertRound;
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

    public ModifyBasicConcertCommand toModifyCommand(long id) {
        List<ConcertRound> concertRounds = IntStream.range(0, rounds.size()).mapToObj(idx ->
            rounds.get(idx).toModel(idx + 1)
        ).toList();

        return ModifyBasicConcertCommand.builder()
            .id(id)
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .rounds(concertRounds)
            .build();
    }
}
