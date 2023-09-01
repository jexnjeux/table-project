package com.example.table.member.exception;

import com.example.table.common.type.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class MemberNotFoundException extends UsernameNotFoundException {

  private ErrorCode errorCode;

  public MemberNotFoundException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public MemberNotFoundException(ErrorCode errorCode, String msg) {
    super(msg);
    this.errorCode = errorCode;
  }

}
