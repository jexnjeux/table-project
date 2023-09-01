package com.example.table.member.dto;

import com.example.table.member.domain.Member;
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
public class MemberDto {

  private Long memberId;
  private MemberType memberType;
  private String username;
  private String name;
  private String phoneNumber;
  private String email;

  public static MemberDto of(Member member) {
    return MemberDto.builder()
        .memberType(member.getMemberType())
        .memberId(member.getId())
        .username(member.getUsername())
        .name(member.getName())
        .phoneNumber(member.getPhoneNumber())
        .email(member.getEmail())
        .build();
  }

}
