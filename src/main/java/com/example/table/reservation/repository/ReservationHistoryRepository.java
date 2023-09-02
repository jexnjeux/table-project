package com.example.table.reservation.repository;

import com.example.table.reservation.domain.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {

}
