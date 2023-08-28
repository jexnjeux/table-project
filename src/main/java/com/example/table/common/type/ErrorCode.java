package com.example.table.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  STORE_NOT_FOUND("매장 정보가 없습니다."),
  STORE_ALREADY_REGISTERED("이미 등록된 매장입니다."),
  MISSING_PARAMETER("검색어를 입력해 주세요."),

  USERNAME_ALREADY_REGISTERED("이미 등록된 아이디입니다."),
  MISSING_REQUEST_BODY("필수 입력 사항을 입력해 주세요.");

  private final String description;

}