package com.example.table.store.exception;

import static com.example.table.store.type.ErrorCode.MISSING_PARAMETER;

import com.example.table.common.dto.ErrorResponse;
import com.example.table.store.type.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(StoreException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleStoreException(StoreException e) {
    return new ErrorResponse(e.getErrorCode(), e.getMessage());
  }


}
