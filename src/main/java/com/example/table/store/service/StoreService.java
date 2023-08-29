package com.example.table.store.service;

import static com.example.table.common.type.ErrorCode.STORE_ALREADY_REGISTERED;
import static com.example.table.common.type.ErrorCode.STORE_NOT_FOUND;

import com.example.table.common.dto.PageResponse;
import com.example.table.store.domain.Store;
import com.example.table.store.dto.StoreDto;
import com.example.table.store.dto.StoreInfo;
import com.example.table.store.exception.StoreException;
import com.example.table.store.repository.StoreRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
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

  public PageResponse<StoreInfo> findStores(String q, Pageable pageable) {
    Page<StoreDto> storeDtoPage = storeRepository.findByStoreNameContainingIgnoreCase(
        q, pageable).map(store -> StoreDto.builder()
        .storeId(store.getId())
        .storeName(store.getStoreName())
        .description(store.getDescription())
        .roadAddress(store.getRoadAddress())
        .detailAddress(store.getDetailAddress())
        .registeredAt(store.getRegisteredAt())
        .build());

    List<StoreInfo> storeInfoList = storeDtoPage.getContent().stream().map(
        storeDto -> StoreInfo.builder()
            .storeName(storeDto.getStoreName())
            .roadAddress(storeDto.getRoadAddress())
            .build()
    ).toList();

    PageResponse<StoreInfo> pageResponse = new PageResponse<>();
    pageResponse.setData(storeInfoList);
    pageResponse.setPageNumber(storeDtoPage.getNumber());
    pageResponse.setPageSize(storeDtoPage.getSize());
    pageResponse.setPageSize(storeDtoPage.getSize());
    pageResponse.setTotalPages(storeDtoPage.getTotalPages());
    pageResponse.setTotalElements(storeDtoPage.getTotalElements());
    pageResponse.setFirst(storeDtoPage.isFirst());
    pageResponse.setLast(storeDtoPage.isLast());

    return pageResponse;
  }

  public Store findStore(Long id) {
    return storeRepository.findById(id).orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
  }


}
