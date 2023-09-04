package com.example.table.review.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewResponse {

  private String username;
  private String storeName;
  private LocalDateTime reservationDate;

  public static ReviewResponse of(ReviewDto reviewDto) {
    return ReviewResponse.builder()
        .username(reviewDto.getUsername())
        .storeName(reviewDto.getStoreName())
        .reservationDate(reviewDto.getReservationDate())
        .build();
  }

}
