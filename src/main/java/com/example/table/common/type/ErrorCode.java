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
  INVALID_RESERVATION_DATE("해당 시간에는 예약이 불가합니다."),
  ALREADY_RESERVED("예약이 존재합니다."),
  TOO_EARLY_CHECKIN("방문 확인은 예약 시간 10분 전부터 가능합니다."),
  RESERVATION_NOT_FOUND("예약이 존재하지 않습니다."),

  CANCELED_RESERVATION("취소된 예약이입니다."),
  EXPIRED_REVIEW_DATE("리뷰가 입력 가능한 기간이 만료되었습니다."),
  RESERVATION_NOT_YET("매장 방문 후 리뷰 입력이 가능합니다."),

  MEMBER_RESERVATION_NOT_MATCH("해당 유저의 예약 내역이 아닙니다."),
  ILLEGAL_REVIEW_RATING("별점은 1점 ~ 5점이 가능합니다.");

  private final String description;

}