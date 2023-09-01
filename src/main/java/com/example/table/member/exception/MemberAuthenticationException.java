package com.example.table.member.exception;

import com.example.table.common.type.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class MemberAuthenticationException extends AuthenticationException {

  private ErrorCode errorCode;

  public MemberAuthenticationException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public MemberAuthenticationException(ErrorCode errorCode, String msg) {
    super(msg);
    this.errorCode = errorCode;
  }

  public MemberAuthenticationException(String msg, Throwable cause) {
    super(msg, cause);
  }


}
