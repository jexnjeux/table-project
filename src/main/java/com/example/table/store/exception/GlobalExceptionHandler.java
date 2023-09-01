package com.example.table.store.exception;

import static com.example.table.common.type.ErrorCode.MISSING_PARAMETER;

import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(StoreException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto handleStoreException(StoreException e) {
    return new ResponseDto(ResponseHeader.fail(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(), e.getMessage()));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {

    return new ResponseDto(ResponseHeader.fail(HttpStatus.BAD_REQUEST.value(), MISSING_PARAMETER,
        e.getParameterName() + " 파라미터를 입력해 주세요."));
  }

}
