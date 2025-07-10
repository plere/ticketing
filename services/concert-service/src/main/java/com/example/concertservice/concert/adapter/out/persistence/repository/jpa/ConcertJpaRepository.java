package com.example.concertservice.concert.adapter.out.persistence.repository.jpa;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertEntity;
import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertEntityMapper;
import com.example.concertservice.concert.adapter.out.persistence.repository.ConcertRepository;
import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ConcertJpaRepository implements ConcertRepository {
    private final SpringDataJpaConcertRepository concertRepository;

    @Override
    public Concert save(Concert concert) {
        return ConcertEntityMapper.mapToModel(
            concertRepository.save(ConcertEntityMapper.mapToEntity(concert))
        );
    }

    @Override
    public Optional<Concert> getConcert(long id) {
        return concertRepository.findById(id).map(ConcertEntityMapper::mapToModel);
    }

    @Override
    public void updateState(Concert concert, ConcertState state) {
        ConcertEntity entity = ConcertEntityMapper.mapToEntity(concert);
        entity.setState(state);

        concertRepository.save(entity);
    }

    @Override
    public void update(Concert concert) {
        concertRepository.save(ConcertEntityMapper.mapToEntity(concert));
    }

    @Override
    public List<Concert> getAllTodoChangeStateToOpen() {
        return concertRepository.findAllByOpenTimeIsBeforeAndState(LocalDateTime.now(), ConcertState.READY);
    }
}
