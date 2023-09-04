package com.example.table.common.security;

import com.example.table.common.utils.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;
  public static final String TOKEN_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String token = resolveTokenFromRequest((HttpServletRequest) request);
    log.info("token: " + token);
    log.info("validate token: " + jwtTokenProvider.validateJwtToken(token));
    if (StringUtils.hasText(token) && jwtTokenProvider.validateJwtToken(token)) {
      Authentication authentication = jwtTokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
//      UserDetails userDetails = jwtTokenProvider.getUserDetailsFromJwtToken(token);
//      log.info("userDetails: " + String.valueOf(userDetails));
//      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//          userDetails, null, userDetails.getAuthorities());
//      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }

  private String resolveTokenFromRequest
      (HttpServletRequest request) {
    String token = request.getHeader(TOKEN_HEADER);
    if (!ObjectUtils.isEmpty(token)) {
      log.info("!ObjectUtils.isEmpty(token)");
      if (token.startsWith(TOKEN_PREFIX)) {
        log.info("token startsWith TOKEN PREFIX");
      }
    }

    if (!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
      return token.substring(TOKEN_PREFIX.length());
    }
    return null;
//    log.info("bearer: " + bearerToken);
//    if (StringUtils.hasText(bearerToken)){
//      log.info("hasToken");
//      if (bearerToken.startsWith("Bearer")){
//        log.info("startsWith");
//      }
//    }
//    if (StringUtils.hasText(bearerToken)) {
//      log.info("bearer split: " + bearerToken.split(" ")[1]);
//      return bearerToken.split(" ")[1];
//    }
//    return null;
  }
}
