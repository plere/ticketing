package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConcertSeatRepository extends JpaRepository<ConcertSeatEntity, Long> {
}
