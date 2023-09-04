package com.example.table.review.exception;

import com.example.table.common.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewException extends RuntimeException{
  ErrorCode errorCode;

  public ReviewException(ErrorCode errorCode){
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public ReviewException(ErrorCode errorCode, String message){
    super(message);
    this.errorCode = errorCode;
  }

}
