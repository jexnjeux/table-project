package com.example.table.reservation.dto;

import com.example.table.reservation.domain.ReservationHistory;
import com.example.table.reservation.type.ReservationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationActionDto {

  private String username;
  private String memberName;
  private String storeName;
  private ReservationStatus status;
  private int guestCount;
  private LocalDateTime confirmationDate;
  private LocalDateTime cancellationDate;
  private LocalDateTime reservationDate;

  public static ReservationActionDto of(ReservationHistory history) {
    return ReservationActionDto.builder()
        .username(history.getMember().getUsername())
        .memberName(history.getMember().getName())
        .storeName(history.getStore().getStoreName())
        .status(history.getStatus())
        .guestCount(history.getGuestCount())
        .confirmationDate(history.getConfirmationDate())
        .cancellationDate(history.getCancellationDate())
        .reservationDate(history.getReservationDate())
        .build();
  }
}
