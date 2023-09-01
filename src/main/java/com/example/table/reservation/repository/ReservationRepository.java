package com.example.table.reservation.repository;

import com.example.table.reservation.domain.Reservation;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  boolean existsByStoreIdAndReservationDate(Long storeId, LocalDateTime reservationDate);

}
