package com.example.admin.concert.service;

import com.example.admin.concert.controller.dto.CreateRequest;
import com.example.admin.concert.model.Concert;
import com.example.admin.concert.model.ConcertSeat;
import com.example.admin.concert.repository.AdminConcertRepository;
import com.example.admin.concert.repository.AdminConcertSeatRepository;
import com.example.admin.concert.service.validation.AdminConcertCreateValidation;
import com.example.admin.place.repository.PlaceSeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminConcertService {
    private final PlaceSeatRepository placeSeatRepository;
    private final AdminConcertRepository concertRepository;
    private final AdminConcertSeatRepository concertSeatRepository;
    private final AdminConcertCreateValidation createValidate;

    @Transactional
    public long create(CreateRequest request) {
        createValidate.validate(request);

        Concert concert = request.toModel();
        List<ConcertSeat> concertSeats = request.toConcertSeatModels(placeSeatRepository.findAllByPlaceId(concert.getPlaceId()), concert.getSeatGrades());

        Long result = concertRepository.save(concert).getId();
        concertSeatRepository.saveAll(concertSeats);

        return result;
    }
}
