package com.example.table.store.exception;

import com.example.table.store.type.ErrorCode;
import lombok.AllArgsConstructor;
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

}
