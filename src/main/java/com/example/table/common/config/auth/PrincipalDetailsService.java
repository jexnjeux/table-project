package com.example.table.common.config.auth;

import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;

import com.example.table.member.domain.Member;
import com.example.table.member.exception.MemberNotFoundException;
import com.example.table.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername(): 시작" + username);
    Member member = memberRepository.findByUsername(username)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
    log.info("loadUserByUsername(): " + member.getUsername());
    return new PrincipalDetails(member);
  }
}
