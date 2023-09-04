package com.example.table.review.dto;

import com.example.table.review.domain.Review;
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
public class ReviewDto {

  private String username;
  private String storeName;
  private LocalDateTime reservationDate;
  public static ReviewDto of(Review review){
    return ReviewDto.builder()
        .username(review.getMember().getUsername())
        .storeName(review.getStore().getStoreName())
        .reservationDate(review.getReservation().getReservationDate())
        .build();
  }
}
