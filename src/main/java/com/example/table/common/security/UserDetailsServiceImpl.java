package com.example.table.common.security;

import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.table.member.type.MemberType.PARTNER;
import static com.example.table.member.type.MemberType.USER;

import com.example.table.member.domain.Member;
import com.example.table.member.exception.MemberNotFoundException;
import com.example.table.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;


  @Override
  public UserDetails loadUserByUsername(String username)
      throws MemberNotFoundException {
    Member member = memberRepository.findByUsername(username)
        .orElseThrow(
            () -> new MemberNotFoundException(MEMBER_NOT_FOUND,
                username + " 사용자 정보가 존재하지 않습니다."));

    List<GrantedAuthority> authorities = new ArrayList<>();
    if (member.getMemberType() == PARTNER) {
      authorities.add(new SimpleGrantedAuthority("PARTNER"));
    } else if (member.getMemberType() == USER) {
      authorities.add(new SimpleGrantedAuthority("USER"));
    }

    return new UserDetailsImpl(member);
  }
}
