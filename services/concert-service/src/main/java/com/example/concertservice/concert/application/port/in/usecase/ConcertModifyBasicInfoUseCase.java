package com.example.concertservice.concert.application.port.in.usecase;

import com.example.concertservice.concert.application.port.in.ModifyBasicConcertCommand;

public interface ConcertModifyBasicInfoUseCase {
    void modifyBasicInfo(ModifyBasicConcertCommand command);
}
