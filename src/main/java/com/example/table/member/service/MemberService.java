package com.example.table.member.service;

import com.example.table.member.dto.MemberDto;
import com.example.table.member.dto.MemberLoginRequest;
import com.example.table.member.dto.MemberRegRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;

public interface MemberService extends UserDetailsService {

  MemberDto registerMember(@Valid MemberRegRequest memberRegRequest, Errors errors);

  UserDetails authenticate(MemberLoginRequest memberLoginRequest);

}
