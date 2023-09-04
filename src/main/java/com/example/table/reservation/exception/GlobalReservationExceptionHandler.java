package com.example.table.reservation.exception;

import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalReservationExceptionHandler {

  @ExceptionHandler(ReservationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseDto reservationExceptionHandler(ReservationException e) {
    return new ResponseDto(ResponseHeader.fail(HttpStatus.CONFLICT.value(), e.getErrorCode(), e.getMessage()));
  }

}
