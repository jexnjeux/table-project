package com.example.table.reservation.dto;

import com.example.table.reservation.validation.ValidReservationDate;
import jakarta.validation.constraints.NotNull;
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
public class ReservationRequest {

  @NotNull(message = "예약자 정보는 필수 입력 사항입니다.")
  private Long memberId;
  @NotNull(message = "예약하는 매장 정보는 필수 입력 사항입니다.")
  private Long storeId;
  @NotNull(message = "예약자 수는 필수 입력 사항입니다.")
  private Integer guestCount;
  @ValidReservationDate
  @NotNull(message = "예약 일자는 필수 입력 사항입니다.")
  private LocalDateTime reservationDate;
  private String memo;
}
