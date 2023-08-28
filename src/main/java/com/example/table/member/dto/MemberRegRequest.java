package com.example.table.member.dto;

import com.example.table.member.type.MemberType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class MemberRegRequest {

  @NotBlank(message = "아이디는 필수 입력 사항입니다.")
  private String username;

  @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "비밀번호는 영문과 숫자를 모두 포함해야 합니다.")
  private String password;
  @NotBlank(message = "휴대폰 번호는 필수 입력 사항입니다.")
  @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
  private String phoneNumber;
  @NotBlank(message = "이메일은 필수 입력 사항입니다.")
  @Email(message = "유효한 이메일 주소를 입력해 주세요.")
  private String email;
  private MemberType memberType = MemberType.USER;
}
