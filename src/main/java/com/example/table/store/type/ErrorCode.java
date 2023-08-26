package com.example.table.store.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  STORE_ALREADY_REGISTERED("이미 등록된 매장입니다."),
  MISSING_PARAMETER("검색어를 입력해 주세요.");

  private final String description;

}
