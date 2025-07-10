package com.example.concertservice.place.adapter.out.persistence.repository.jpa;

import com.example.concertservice.place.adapter.out.persistence.entity.PlaceSeatEntity;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaPlaceSeatRepository extends JpaRepository<PlaceSeatEntity, Long> {
    @OrderBy("floor asc, rowOrder asc")
    List<PlaceSeatEntity> findAllByPlaceId(Long placeId);
}
