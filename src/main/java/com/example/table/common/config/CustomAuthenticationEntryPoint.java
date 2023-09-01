package com.example.table.common.config;

import static com.example.table.common.type.ErrorCode.UNAUTHORIZED_ACCESS;

import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    ResponseDto responseDto = new ResponseDto(
        ResponseHeader.fail(HttpStatus.UNAUTHORIZED.value(), UNAUTHORIZED_ACCESS));

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    objectMapper.writeValue(response.getWriter(), responseDto);
  }
}
