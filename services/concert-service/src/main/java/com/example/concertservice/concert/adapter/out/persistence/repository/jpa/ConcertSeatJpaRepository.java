package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.mapper.ConcertSeatEntityMapper;
import com.example.concertservice.concert.adapter.out.persistence.repository.ConcertSeatRepository;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertSeatJpaRepository implements ConcertSeatRepository {
    private final SpringDataJpaConcertSeatRepository concertSeatRepository;

    @Override
    public List<ConcertSeat> getAllByRoundIdAndState(long roundId, ConcertSeatState state) {
        return concertSeatRepository.findByRoundIdAndStateIs(roundId, state)
            .stream().map(ConcertSeatEntityMapper::mapToModel).toList();
    }
}
