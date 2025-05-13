package com.example.admin.concert.repository;

import com.example.admin.concert.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConcertRepository extends JpaRepository<Concert, Long> {
}
