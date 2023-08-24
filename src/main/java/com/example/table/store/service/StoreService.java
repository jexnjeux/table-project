package com.example.table.store.service;

import static com.example.table.store.type.ErrorCode.STORE_ALREADY_REGISTERED;

import com.example.table.store.domain.Store;
import com.example.table.store.dto.StoreDto;
import com.example.table.store.exception.StoreException;
import com.example.table.store.repository.StoreRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreRepository storeRepository;

  public StoreDto addStore(String storeName, String roadAddress, String detailAddress,
      String description) {

    if (storeRepository.countByStoreNameAndRoadAddress(storeName, roadAddress) > 0) {
      throw new StoreException(STORE_ALREADY_REGISTERED);
    }

    return StoreDto.of(storeRepository.save(Store.builder()
        .storeName(storeName)
        .description(description)
        .roadAddress(roadAddress)
        .detailAddress(detailAddress)
        .registeredAt(LocalDateTime.now())
        .build()));
  }


}
