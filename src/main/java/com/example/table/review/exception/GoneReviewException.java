package com.example.table.review.exception;

import com.example.table.common.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GoneReviewException extends RuntimeException {

  ErrorCode errorCode;

  public GoneReviewException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }


}
