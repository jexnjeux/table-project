package com.example.table.member.controller;

import com.example.table.member.dto.MemberRegRequest;
import com.example.table.member.dto.MemberResponse;
import com.example.table.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/signup")
  public MemberResponse memberRegister(@RequestBody @Valid MemberRegRequest memberRegRequest, Errors errors) {
    return MemberResponse.of(memberService.registerMember(memberRegRequest, errors));
  }
}
