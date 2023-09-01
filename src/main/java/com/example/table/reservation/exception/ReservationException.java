package com.example.table.reservation.exception;

import com.example.table.common.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservationException extends RuntimeException {

  private ErrorCode errorCode;

  public ReservationException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public ReservationException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

}
