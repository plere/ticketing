package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertSeatEntity;
import com.example.concertservice.concert.domain.ConcertSeatState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaConcertSeatRepository extends JpaRepository<ConcertSeatEntity, Long> {
    List<ConcertSeatEntity> findByRoundIdAndStateIs(long roundId, ConcertSeatState state);
}
