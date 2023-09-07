package com.example.table.common.config.jwt;

import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.table.common.config.auth.PrincipalDetails;
import com.example.table.member.dto.MemberLoginRequest;
import com.example.table.member.exception.MemberNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  //  @Value("${spring.jwt.secret}")
//  private String secret;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    log.info("JwtAuthenticationFilter 진입");
    ObjectMapper objectMapper = new ObjectMapper();
    MemberLoginRequest memberLoginRequest;
    try {
      memberLoginRequest = objectMapper.readValue(request.getInputStream(),
          MemberLoginRequest.class);

      log.info("JwtAuthenticationFilter request: " + memberLoginRequest);

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          memberLoginRequest.getUsername(), memberLoginRequest.getPassword());

      log.info("JwtAuthenticationFilter token: " + authenticationToken);

      Authentication authentication = authenticationManager.authenticate(authenticationToken);

      PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
      log.info("JwtAuthentication: " + principalDetails.getUsername());
      return authentication;
    } catch (IOException e) {
      throw new MemberNotFoundException(MEMBER_NOT_FOUND);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
    int expirationTime = 1000 * 60 * 10;
    String secret = "dGFibGUtc3ByaW5nLWJvb3QtcHJvamVjdC1qd3Qtc2VjcmV0LWtleQo";
    String jwt = JWT.create()
        .withSubject(principalDetails.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
        .withClaim("id", principalDetails.getMember().getId())
        .withClaim("username", principalDetails.getUsername())
        .sign(Algorithm.HMAC512(secret));
    response.addHeader("Authorization", "Bearer " + jwt);

  }
}
