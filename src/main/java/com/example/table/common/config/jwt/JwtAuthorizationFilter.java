package com.example.table.common.config.jwt;

import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.table.common.config.auth.PrincipalDetails;
import com.example.table.member.domain.Member;
import com.example.table.member.exception.MemberNotFoundException;
import com.example.table.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private MemberRepository memberRepository;

  public JwtAuthorizationFilter(
      AuthenticationManager authenticationManager,
      MemberRepository memberRepository) {
    super(authenticationManager);
    this.memberRepository = memberRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String headerString = "Authorization";
    String header = request.getHeader(headerString);
    String secret = "dGFibGUtc3ByaW5nLWJvb3QtcHJvamVjdC1qd3Qtc2VjcmV0LWtleQo";
    log.info("header: " + header);
    String tokenPrefix = "Bearer ";
    if (header == null || !header.startsWith(tokenPrefix)) {
      chain.doFilter(request, response);
      return;
    }
    String token = header.replace(tokenPrefix, "");

    String username = JWT.require(Algorithm.HMAC512(secret)).build().verify(token)
        .getClaim("username").asString();

    if (username != null) {
      Member member = memberRepository.findByUsername(username)
          .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

      PrincipalDetails principalDetails = new PrincipalDetails(member);
      log.info("authorities: " + principalDetails.getAuthorities().toString());
      Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
          principalDetails.getPassword(), principalDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    chain.doFilter(request, response);

  }
}
