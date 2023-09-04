package com.example.table.review.dto;

import com.example.table.review.type.ReviewRating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReviewRequest {

  private Long reservationId;
  private Long memberId;
  @NotBlank(message = "리뷰 제목은 필수 입력 항목입니다.")
  private String title;
  @NotBlank(message = "리뷰 내용은 필수 입력 항목입니다.")
  private String contents;
  @NotNull(message = "평점은 필수 입력 항목입니다.")
  private ReviewRating rating;

}
