package com.example.table.member.service;

import com.example.table.member.dto.MemberDto;
import com.example.table.member.dto.MemberLoginRequest;
import com.example.table.member.dto.MemberRegRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;

public interface MemberService {

  MemberDto registerMember(@Valid MemberRegRequest memberRegRequest, Errors errors);

  UserDetails loginMember(MemberLoginRequest memberLoginRequest);

}
