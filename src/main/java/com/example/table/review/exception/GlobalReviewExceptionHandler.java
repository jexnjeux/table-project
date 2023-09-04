package com.example.table.review.exception;


import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalReviewExceptionHandler {

  @ExceptionHandler(UnprocessableReviewException.class)
  public ResponseDto unprocessableReviewExceptionHandler(UnprocessableReviewException e) {
    log.error("UnprocessableReviewException occurred: " + e.getMessage(), e);
    return new ResponseDto(
        ResponseHeader.fail(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getErrorCode(),
            e.getMessage()));
  }

  @ExceptionHandler(GoneReviewException.class)
  public ResponseDto goneReviewExceptionHandler(GoneReviewException e) {
    log.error("GoneReviewException occurred: " + e.getMessage(), e);
    return new ResponseDto(
        ResponseHeader.fail(HttpStatus.GONE.value(), e.getErrorCode(), e.getMessage()));
  }

  @ExceptionHandler(ForbiddenReviewException.class)
  public ResponseDto forbiddenReviewExceptionHandler(ForbiddenReviewException e) {
    log.error("ForbiddenException occurred: " + e.getMessage(), e);
    return new ResponseDto(
        ResponseHeader.fail(HttpStatus.FORBIDDEN.value(), e.getErrorCode(), e.getMessage()));
  }

  @ExceptionHandler(ReviewException.class)
  public ResponseDto reviewExceptionHandler(ReviewException e) {
    log.error("ReviewException occurred: " + e.getMessage(), e);
    return new ResponseDto(
        ResponseHeader.fail(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(), e.getMessage()));
  }

}
