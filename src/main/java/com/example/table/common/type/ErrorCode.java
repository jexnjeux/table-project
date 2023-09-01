package com.example.table.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  UNAUTHORIZED_ACCESS("로그인 후 이용 가능합니다."),
  STORE_NOT_FOUND("매장 정보가 없습니다."),
  STORE_ALREADY_REGISTERED("이미 등록된 매장입니다."),
  MISSING_PARAMETER("검색어를 입력해 주세요."),

  USERNAME_ALREADY_REGISTERED("이미 등록된 아이디입니다."),
  MISSING_REQUEST_BODY("필수 입력 사항을 입력해 주세요."),
  MEMBER_NOT_FOUND("사용자 정보가 없습니다."),
  LOGIN_FAILED("로그인에 실패하였습니다."),
  USERNAME_NOT_CORRECT("사용자 정보가 올바르지 않습니다"),
  PASSWORD_NOT_CORRECT("비밀번호가 올바르지 않습니다."),

  FULLED_RESERVATION("해당 시간에는 예약이 불가합니다."),
  ALREADY_RESERVED("예약이 존재합니다.");

  private final String description;

}