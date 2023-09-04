package com.example.table.reservation.scheduler;

import static com.example.table.reservation.type.ReservationStatus.CANCELED;
import static com.example.table.reservation.type.ReservationStatus.RESERVED;

import com.example.table.reservation.domain.Reservation;
import com.example.table.reservation.domain.ReservationHistory;
import com.example.table.reservation.repository.ReservationHistoryRepository;
import com.example.table.reservation.repository.ReservationRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@EnableCaching
@AllArgsConstructor
public class CancelScheduler {

  private final ReservationRepository reservationRepository;
  private final ReservationHistoryRepository reservationHistoryRepository;


  @Transactional
  @Scheduled(fixedRate = 60000)
  public void cancelReservationScheduling() {
    log.info("scheduler starts");
    List<Reservation> reservationList = reservationRepository.findByReservationDateBeforeAndStatus(
        LocalDateTime.now(), RESERVED);

    reservationList.forEach(reservation -> {
      reservation.setStatus(CANCELED);
      reservationRepository.save(reservation);
      reservationHistoryRepository.save(ReservationHistory.builder()
          .status(CANCELED)
          .guestCount(reservation.getGuestCount())
          .member(reservation.getMember())
          .store(reservation.getStore())
          .reservationDate(reservation.getReservationDate())
          .cancellationDate(LocalDateTime.now())
          .build());
      log.info("scheduler: " + reservation.getId());
    });

  }
}
