package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertSeatEntity;
import com.example.concertservice.concert.domain.ConcertSeatState;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SpringDataJpaConcertSeatRepository extends JpaRepository<ConcertSeatEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select cs from ConcertSeatEntity cs where cs.id in :seatIds")
    List<ConcertSeatEntity> findAllWithLock(Set<Long> seatIds);

    List<ConcertSeatEntity> findByRoundId(long roundId);

    List<ConcertSeatEntity> findByRoundIdAndStateIs(long roundId, ConcertSeatState state);

    @Modifying
    @Query("update ConcertSeatEntity cs SET cs.state = :state WHERE cs.id in (:ids)")
    void updateState(Set<Long> ids, ConcertSeatState state);
}
