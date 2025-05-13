package com.example.admin.concert.repository;

import com.example.admin.concert.model.ConcertSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConcertSeatRepository extends JpaRepository<ConcertSeat, Long> {
}
