package com.example.admin.place.repository;


import com.example.admin.place.model.PlaceSeat;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceSeatRepository extends JpaRepository<PlaceSeat, Long> {
    @OrderBy("floor asc, rowOrder asc")
    List<PlaceSeat> findAllByPlaceId(Long placeId);
}
