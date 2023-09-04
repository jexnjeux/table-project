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
  private String token;

  public static MemberLoginDetails of(UserDetails userDetails, String jwt) {
    return MemberLoginDetails.builder()
        .username(userDetails.getUsername())
        .authority(userDetails.getAuthorities().iterator().next().getAuthority())
        .token(jwt)
        .build();
  }
}
