package com.example.table.reservation.dto;

import com.example.table.member.domain.Member;
import com.example.table.reservation.domain.Reservation;
import com.example.table.store.domain.Store;
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
public class ReservationDto {

  private Member member;
  private Store store;
  private int guestCount;
  private LocalDateTime reservationDate;
  private String memo;


  public static ReservationDto of(Reservation reservation) {
    return ReservationDto.builder()
        .member(reservation.getMember())
        .store(reservation.getStore())
        .guestCount(reservation.getGuestCount())
        .reservationDate(reservation.getReservationDate())
        .build();
  }
}
