package com.example.table.store.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  STORE_ALREADY_REGISTERED("이미 등록된 매장입니다.");
  private final String description;

}
