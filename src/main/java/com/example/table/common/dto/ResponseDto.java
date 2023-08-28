package com.example.table.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

  private ResponseHeader header;
  private Object body;

  public ResponseDto(ResponseHeader header) {
    this.header = header;
  }

}
