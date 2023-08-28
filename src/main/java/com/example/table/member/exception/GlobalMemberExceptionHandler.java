package com.example.table.member.exception;

import static com.example.table.common.type.ErrorCode.MISSING_REQUEST_BODY;

import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalMemberExceptionHandler {

  @ExceptionHandler(MemberException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto memberExceptionHandler(MemberException e) {
    return new ResponseDto(ResponseHeader.fail(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(), e.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
    return new ResponseDto(ResponseHeader.fail(HttpStatus.BAD_REQUEST.value(), MISSING_REQUEST_BODY));
  }


}
