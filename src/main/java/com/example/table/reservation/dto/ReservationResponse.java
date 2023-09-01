package com.example.table.reservation.dto;

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
public class ReservationResponse {

  private String username;
  private String memberName;
  private String storeName;
  private int guestCount;
  private LocalDateTime reservationDate;
  private String memo;

  public static ReservationResponse of(ReservationDto reservationDto) {
    return ReservationResponse.builder()
        .username(reservationDto.getMember().getUsername())
        .memberName(reservationDto.getMember().getName())
        .storeName(reservationDto.getStore().getStoreName())
        .guestCount(reservationDto.getGuestCount())
        .reservationDate(reservationDto.getReservationDate())
        .build();
  }

}
