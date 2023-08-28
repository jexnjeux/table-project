package com.example.table.member.dto;

import com.example.table.member.type.MemberType;
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
public class MemberResponse {

  private Long memberId;
  private MemberType memberType;
  private String username;
  private String phoneNumber;
  private String email;

  public static MemberResponse of(MemberDto memberDto) {
    return MemberResponse.builder()
        .memberId(memberDto.getMemberId())
        .memberType(memberDto.getMemberType())
        .username(memberDto.getUsername())
        .phoneNumber(memberDto.getPhoneNumber())
        .email(memberDto.getEmail())
        .build();
  }
}
