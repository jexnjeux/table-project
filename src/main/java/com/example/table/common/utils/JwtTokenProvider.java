package com.example.table.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;
  private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 4;
  @Value("$(spring.jwt.secret)")
  private String secretKey;


  public String generateJwtToken(String username) {
    Date expirationDate = new Date(TOKEN_EXPIRATION_TIME);

    Map<String, Object> claims = new HashMap<>();

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  public Authentication getAuthentication(String jwt) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromJwtToken(jwt));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsernameFromJwtToken(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  public UserDetails getUserDetailsFromJwtToken(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody();

    String username = claims.getSubject();

    List<String> authorities = claims.get("authorities", List.class);

    return new User(username, "", authorities.stream().map(
        SimpleGrantedAuthority::new).collect(Collectors.toList()));
  }

  public boolean validateJwtToken(String token) {
    if (!StringUtils.hasText(token)) {
      return false;
    }
    var claims = parseClaims(token);
    return claims.getExpiration().before(new Date());
  }

  private Claims parseClaims(String token) {
    try {
      return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

}
