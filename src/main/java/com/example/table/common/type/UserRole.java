package com.example.table.common.type;

import lombok.Getter;

@Getter
public enum UserRole {
  PARTNER("PARTNER"),
  USER("USER");

  UserRole(String value) {
    this.value = value;
  }

  private String value;
}
