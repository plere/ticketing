package com.example.admin.concert.scheduler;

import com.example.admin.concert.model.Concert;
import com.example.admin.concert.model.ConcertState;
import com.example.admin.concert.repository.AdminConcertRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertUpdateStateScheduler {
    private final AdminConcertRepository adminConcertRepository;

    @Scheduled(
        cron = "0 0,30 * * * *"
    )
    @Transactional
    public void toOpenExecutor() {
        List<Concert> concerts = adminConcertRepository.findAllByOpenTimeIsBeforeAndState(LocalDateTime.now(), ConcertState.READY);
        concerts.forEach(Concert::updateStateToOpen);
    }
}
