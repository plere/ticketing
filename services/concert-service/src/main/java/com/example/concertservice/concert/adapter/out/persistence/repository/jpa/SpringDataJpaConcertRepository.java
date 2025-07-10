package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertEntity;
import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataJpaConcertRepository extends JpaRepository<ConcertEntity, Long> {
    List<Concert> findAllByOpenTimeIsBeforeAndState(LocalDateTime openTime, ConcertState state);
}
