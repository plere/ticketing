package com.example.admin.concert.service;

import com.example.admin.concert.model.ConcertSeat;
import com.example.admin.concert.repository.AdminConcertSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminConcertSeatService {
    private final AdminConcertSeatRepository concertSeatRepository;

    public void create(List<ConcertSeat> concertSeats) {
        concertSeatRepository.saveAll(concertSeats);
    }

    public void update(long concertId, List<ConcertSeat> concertSeats) {
        concertSeatRepository.deleteAllByConcertId(concertId);
        concertSeatRepository.saveAll(concertSeats);
    }


}
