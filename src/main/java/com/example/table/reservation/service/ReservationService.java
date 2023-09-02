package com.example.table.reservation.service;

import com.example.table.reservation.dto.ReservationActionDto;
import com.example.table.reservation.dto.ReservationDto;
import com.example.table.reservation.dto.ReservationRequest;
import org.springframework.validation.Errors;

public interface ReservationService {

  ReservationDto createReservation(ReservationRequest reservationRequest, Errors errors);
  ReservationActionDto confirmReservation(String phoneNumber);
}
