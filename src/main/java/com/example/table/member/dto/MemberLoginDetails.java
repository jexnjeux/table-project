package com.example.table.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDetails {

  private String username;
  private String authority;

  public static MemberLoginDetails of(UserDetails userDetails) {
    return MemberLoginDetails.builder()
        .username(userDetails.getUsername())
        .authority(userDetails.getAuthorities().iterator().next().getAuthority())
        .build();
  }
}
