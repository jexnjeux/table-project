package com.example.table.member.service;

import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.table.common.type.ErrorCode.MISSING_REQUEST_BODY;
import static com.example.table.common.type.ErrorCode.USERNAME_ALREADY_REGISTERED;
import static com.example.table.member.type.MemberType.PARTNER;
import static com.example.table.member.type.MemberType.USER;

import com.example.table.member.domain.Member;
import com.example.table.member.dto.MemberDto;
import com.example.table.member.dto.MemberLoginRequest;
import com.example.table.member.dto.MemberRegRequest;
import com.example.table.member.exception.MemberException;
import com.example.table.member.exception.MemberNotFoundException;
import com.example.table.member.repository.MemberRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public MemberDto registerMember(@Valid MemberRegRequest memberRegRequest, Errors errors) {

    if (errors.hasErrors()) {
      FieldError fieldError = errors.getFieldError();
      if (fieldError != null) {
        throw new MemberException(MISSING_REQUEST_BODY, fieldError.getDefaultMessage());
      } else {
        throw new MemberException(MISSING_REQUEST_BODY);
      }
    }

    Optional<Member> member = memberRepository.findByUsername(memberRegRequest.getUsername());

    if (member.isPresent()) {
      throw new MemberException(USERNAME_ALREADY_REGISTERED);
    }

    return MemberDto.of(memberRepository.save(Member.builder()
        .memberType(memberRegRequest.getMemberType())
        .username(memberRegRequest.getUsername())
        .password(passwordEncoder.encode(memberRegRequest.getPassword()))
        .phoneNumber(memberRegRequest.getPhoneNumber())
        .email(memberRegRequest.getEmail())
        .registeredAt(LocalDateTime.now())
        .build()));
  }

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
      // 추가적인 PARTNER 권한을 설정하려면 여기에 추가
    } else if (member.getMemberType() == USER) {
      authorities.add(new SimpleGrantedAuthority("USER"));
    }

    return new User(member.getUsername(), member.getPassword(), authorities);
  }

  public UserDetails authenticate(MemberLoginRequest memberLoginRequest) {

    UserDetails userDetails = loadUserByUsername(memberLoginRequest.getUsername());

      if (!passwordEncoder.matches(memberLoginRequest.getPassword(), userDetails.getPassword())) {
        throw new BadCredentialsException("로그인에 실패하였습니다.");
      }

    return userDetails;
  }

}
