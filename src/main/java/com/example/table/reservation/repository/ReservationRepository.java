package com.example.table.reservation.repository;

import com.example.table.reservation.domain.Reservation;
import com.example.table.reservation.type.ReservationStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  boolean existsByStoreIdAndReservationDate(Long storeId, LocalDateTime reservationDate);
  Optional<Reservation> findByMemberPhoneNumber(String phoneNumber);

  List<Reservation> findByReservationDateBeforeAndStatus(LocalDateTime currentTime, ReservationStatus status);
}
