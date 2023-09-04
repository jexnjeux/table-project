package com.example.table.review.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewRating {
  ONE_STAR("1"),
  TWO_STARS("2"),
  THREE_STARS("3"),
  FOUR_STARS("4"),
  FIVE_STARS("5");

  private final String value;
}
