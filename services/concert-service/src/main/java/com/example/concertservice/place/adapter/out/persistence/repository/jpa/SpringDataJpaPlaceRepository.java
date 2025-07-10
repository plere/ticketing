package com.example.concertservice.place.adapter.out.persistence.repository.jpa;

import com.example.concertservice.place.adapter.out.persistence.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaPlaceRepository extends JpaRepository<PlaceEntity, Long> {
}
