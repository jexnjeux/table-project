package com.example.table.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest {

  @NotBlank(message = "아이디는 필수 입력 사항입니다.")
  private String username;
  @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
  private String password;

}
