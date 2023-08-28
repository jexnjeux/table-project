package com.example.table.member.service;

import com.example.table.member.dto.MemberDto;
import com.example.table.member.dto.MemberRegRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


@Service
public interface MemberService {

  MemberDto registerMember(MemberRegRequest memberRegRequest, Errors errors);
}
