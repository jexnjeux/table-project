package com.example.table.store.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

  private Long storeId;
  private String storeName;
  private String description;
  private String roadAddress;
  private String detailAddress;
  private LocalDateTime registeredAt;

  public static StoreResponse of(StoreDto storeDto) {
    return StoreResponse.builder()
        .storeId(storeDto.getStoreId())
        .storeName(storeDto.getStoreName())
        .description(storeDto.getDescription())
        .roadAddress(storeDto.getRoadAddress())
        .detailAddress(storeDto.getDetailAddress())
        .registeredAt(storeDto.getRegisteredAt())
        .build();
  }

}
