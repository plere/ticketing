package com.example.admin.concert.repository;

import com.example.admin.concert.model.Concert;
import com.example.admin.concert.model.ConcertState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdminConcertRepository extends JpaRepository<Concert, Long> {
    List<Concert> findAllByOpenTimeIsBeforeAndState(LocalDateTime openTime, ConcertState state);

}
