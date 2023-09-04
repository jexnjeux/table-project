package com.example.table.reservation.controller;

import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import com.example.table.reservation.dto.ReservationActionDto;
import com.example.table.reservation.dto.ReservationRequest;
import com.example.table.reservation.dto.ReservationResponse;
import com.example.table.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/reservation")
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  @PreAuthorize("hasAuthority('USER')")
  public ReservationResponse createReservation(
      @RequestBody @Valid ReservationRequest reservationRequest, Errors errors) {
    return ReservationResponse.of(reservationService.createReservation(reservationRequest, errors));
  }

  @PreAuthorize("hasAuthority('USER')")
  @PutMapping("/confirm")
  public ResponseEntity<?> confirmReservation(@RequestParam("phoneNumber") String phoneNumber){
    ReservationActionDto reservationActionDto = reservationService.confirmReservation(phoneNumber);
    return ResponseEntity.ok(new ResponseDto(ResponseHeader.success(), reservationActionDto));
  }
}
