package com.example.table.store.controller;

import com.example.table.common.dto.PageResponse;
import com.example.table.store.domain.Store;
import com.example.table.store.dto.StoreDto;
import com.example.table.store.dto.StoreInfo;
import com.example.table.store.dto.StoreRequest;
import com.example.table.store.dto.StoreResponse;
import com.example.table.store.service.StoreService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

  private final StoreService storeService;

  @PostMapping
  public StoreResponse storeAdd(@RequestBody @Valid StoreRequest storeRequest) {

    return StoreResponse.of(storeService.addStore(storeRequest.getStoreName(),
        storeRequest.getRoadAddress(), storeRequest.getDetailAddress(),
        storeRequest.getDescription()));
  }

  @GetMapping
  public PageResponse<StoreInfo> storeList(@RequestParam("q") String q,
      @PageableDefault() Pageable pageable) {
    Page<StoreDto> storeDtoPage = storeService.findStores(q, pageable);
    List<StoreInfo> storeInfoList = storeDtoPage.getContent().stream().map(
        storeDto -> StoreInfo.builder()
            .storeName(storeDto.getStoreName())
            .roadAddress(storeDto.getRoadAddress())
            .build()
    ).toList();

    return new PageResponse<>
        (storeInfoList, storeDtoPage.getNumber(), storeDtoPage.getSize(),
            storeDtoPage.getTotalPages(), storeDtoPage.getTotalElements(), storeDtoPage.isFirst(),
            storeDtoPage.isLast());

  }

  @GetMapping("/{id}")
  public Store storeDetail(@PathVariable Long id) {
    return storeService.findStore(id);
  }

}
