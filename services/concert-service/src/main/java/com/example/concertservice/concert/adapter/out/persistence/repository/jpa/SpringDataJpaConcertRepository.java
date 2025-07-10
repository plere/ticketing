package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertEntity;
import com.example.concertservice.concert.domain.ConcertState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataJpaConcertRepository extends JpaRepository<ConcertEntity, Long> {
    List<ConcertEntity> findAllByOpenTimeIsBeforeAndState(LocalDateTime openTime, ConcertState state);

    Page<ConcertEntity> findByState(Pageable pageable, ConcertState state);
}
