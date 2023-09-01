package com.example.table.reservation.controller;

import com.example.table.reservation.dto.ReservationRequest;
import com.example.table.reservation.dto.ReservationResponse;
import com.example.table.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/reservation")
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ReservationResponse createReservation(
      @RequestBody @Valid ReservationRequest reservationRequest, Errors errors) {
    return ReservationResponse.of(reservationService.createReservation(reservationRequest, errors));

  }
}
