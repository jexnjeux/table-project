package com.example.table.store.dto;

import com.example.table.store.domain.Store;
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
public class StoreDto {

  private Long storeId;
  private String storeName;
  private String description;
  private String roadAddress;
  private String detailAddress;
  private LocalDateTime registeredAt;

  public static StoreDto of(Store store) {
    return StoreDto.builder()
        .storeId(store.getId())
        .storeName(store.getStoreName())
        .description(store.getDescription())
        .roadAddress(store.getRoadAddress())
        .detailAddress(store.getDetailAddress())
        .registeredAt(store.getRegisteredAt())
        .build();
  }

}
