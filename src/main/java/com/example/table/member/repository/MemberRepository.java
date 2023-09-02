package com.example.table.member.repository;

import com.example.table.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUsername(String username);
  Optional<Member> findByPhoneNumber(String phoneNumber);
}
