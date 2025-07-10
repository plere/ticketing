package com.example.concertservice.concert.application.port.in.usecase;

import com.example.concertservice.concert.application.port.in.ModifyConcertPlaceCommand;

public interface ConcertModifyPlaceInfoUseCase {
    void modifyPlaceInfo(ModifyConcertPlaceCommand command);
}
