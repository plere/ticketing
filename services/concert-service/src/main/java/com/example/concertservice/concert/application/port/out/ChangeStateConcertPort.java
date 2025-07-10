package com.example.concertservice.concert.application.port.out;

public interface ChangeStateConcertPort {
    void changeStateToClose(long id);

    void changeStateToOpen(long id);
}
