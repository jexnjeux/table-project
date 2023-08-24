package com.example.table.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {

  @NotBlank(message = "매장명은 필수 입력사항입니다.")
  private String storeName;
  @NotBlank(message = "주소는 필수 입력사항입니다.")
  private String roadAddress;
  @NotBlank(message = "주소는 필수 입력사항입니다.")
  private String detailAddress;
  @NotBlank(message = "매장 설명은 필수 입력사항입니다.")
  private String description;


}
