package com.example.table.common.dto;

import com.example.table.store.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHeader {

  private boolean success;
  private int statusCode;
  private ErrorCode resultCode;
  private String message;

  public static ResponseHeader success() {
    return ResponseHeader.builder()
        .success(true)
        .statusCode(HttpStatus.OK.value())
        .build();
  }

  public static ResponseHeader fail(int statusCode, ErrorCode errorCode) {
    return ResponseHeader.builder()
        .success(false)
        .statusCode(statusCode)
        .resultCode(errorCode)
        .message(errorCode.getDescription())
        .build();
  }

  public static ResponseHeader fail(int statusCode, ErrorCode errorCode, String message) {
    return ResponseHeader.builder()
        .success(false)
        .statusCode(statusCode)
        .resultCode(errorCode)
        .message(errorCode.getDescription())
        .message(message)
        .build();
  }

}
