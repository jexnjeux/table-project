package com.example.table.store.exception;

import com.example.table.common.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(StoreException.class)
  public ErrorResponse handleStoreException(StoreException e) {
    return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
  }

}
