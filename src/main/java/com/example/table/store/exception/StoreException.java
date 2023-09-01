package com.example.table.store.exception;

import com.example.table.common.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class StoreException extends RuntimeException {

  private ErrorCode errorCode;

  public StoreException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public StoreException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

}
