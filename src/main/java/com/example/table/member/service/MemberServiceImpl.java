package com.example.table.member.service;

import static com.example.table.common.type.ErrorCode.MISSING_REQUEST_BODY;
import static com.example.table.common.type.ErrorCode.USERNAME_ALREADY_REGISTERED;

import com.example.table.member.domain.Member;
import com.example.table.member.dto.MemberDto;
import com.example.table.member.dto.MemberRegRequest;
import com.example.table.member.exception.MemberException;
import com.example.table.member.repository.MemberRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
        .role(memberRegRequest.getRole())
        .username(memberRegRequest.getUsername())
        .password(passwordEncoder.encode(memberRegRequest.getPassword()))
        .name(memberRegRequest.getName())
        .phoneNumber(memberRegRequest.getPhoneNumber())
        .email(memberRegRequest.getEmail())
        .registeredAt(LocalDateTime.now())
        .build()));
  }


}
