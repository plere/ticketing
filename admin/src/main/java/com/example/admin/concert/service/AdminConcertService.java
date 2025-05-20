package com.example.admin.concert.service;

import com.example.admin.concert.controller.dto.CreateRequest;
import com.example.admin.concert.controller.dto.ModifyConcertBasicRequest;
import com.example.admin.concert.controller.dto.ModifyConcertPlaceRequest;
import com.example.admin.concert.model.Concert;
import com.example.admin.concert.model.dto.ModifyConcertPlaceDto;
import com.example.admin.concert.port.out.AdminPlacePort;
import com.example.admin.concert.repository.AdminConcertRepository;
import com.example.admin.concert.service.validation.AdminConcertCreateValidation;
import com.example.admin.concert.service.validation.AdminConcertModifyBasicValidation;
import com.example.admin.concert.service.validation.AdminConcertModifyPlaceValidation;
import com.example.httpresponse.exception.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.admin.concert.controller.dto.AdminConcertErrorResponseCode.NOT_FOUND_CONCERT_ERROR;

@Service
@RequiredArgsConstructor
public class AdminConcertService {
    private final AdminConcertRepository concertRepository;
    private final AdminConcertCreateValidation createValidate;
    private final AdminConcertModifyBasicValidation modifyBasicValidation;
    private final AdminConcertModifyPlaceValidation modifyPlaceValidation;
    private final AdminConcertSeatService seatService;
    private final AdminPlacePort placePort;


    @Transactional
    public long create(CreateRequest request) {
        createValidate.validate(request.toValidationDto());

        Concert concert = request.toModel();

        Long concertId = concertRepository.save(concert).getId();

        seatService.create(
            request.toConcertSeatModels(concertId,
                placePort.getAllSeatsByPlaceId(concert.getPlaceId())
                , concert.getSeatGrades())
        );

        return concertId;
    }

    @Transactional
    public void modifyBasic(long id, ModifyConcertBasicRequest request) {
        modifyBasicValidation.validate(id, request);

        Concert concert = concertRepository.findById(id).get();

        concert.modifyBasic(request.toModifyDto());
    }

    @Transactional
    public void modifyPlace(long id, ModifyConcertPlaceRequest request) {
        modifyPlaceValidation.validate(id, request);

        Concert concert = concertRepository.findById(id).get();

        ModifyConcertPlaceDto modifyDto = request.toModifyDto(
            concert.getId(), placePort.getAllSeatsByPlaceId(concert.getPlaceId())
        );

        concert.modifyPlace(modifyDto);
        seatService.update(concert.getId(), modifyDto.seats());
    }

    @Transactional
    public void updateStateToClose(long id) {
        Concert concert = concertRepository.findById(id).orElseThrow(() -> new BadRequestException(NOT_FOUND_CONCERT_ERROR, NOT_FOUND_CONCERT_ERROR.getErrorMessage()));
        concert.updateStateToClose();
    }
}
