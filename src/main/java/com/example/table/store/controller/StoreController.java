package com.example.table.store.controller;

import com.example.table.common.dto.PageResponse;
import com.example.table.common.dto.ResponseDto;
import com.example.table.common.dto.ResponseHeader;
import com.example.table.store.domain.Store;
import com.example.table.store.dto.StoreInfo;
import com.example.table.store.dto.StoreRequest;
import com.example.table.store.dto.StoreResponse;
import com.example.table.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
  public StoreResponse registerStore(@RequestBody @Valid StoreRequest storeRequest) {

    return StoreResponse.of(storeService.addStore(storeRequest.getStoreName(),
        storeRequest.getRoadAddress(), storeRequest.getDetailAddress(),
        storeRequest.getDescription()));
  }

  @GetMapping
  public ResponseEntity<?> getStoreList(@RequestParam("q") String q,
      @PageableDefault() Pageable pageable) {
    PageResponse<StoreInfo> pageResponse = storeService.findStores(q, pageable);

    return ResponseEntity.ok().body(new ResponseDto(ResponseHeader.success(), pageResponse));

  }

  @GetMapping("/{id}")
  public Store getStoreDetail(@PathVariable Long id) {
    return storeService.findStore(id);
  }

}
