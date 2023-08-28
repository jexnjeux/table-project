package com.example.table.member.exception;

import com.example.table.common.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class MemberException extends RuntimeException {

  private ErrorCode errorCode;

  public MemberException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public MemberException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}
