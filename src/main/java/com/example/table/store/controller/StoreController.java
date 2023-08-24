package com.example.table.store.controller;

import com.example.table.store.dto.StoreRequest;
import com.example.table.store.dto.StoreResponse;
import com.example.table.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

  private final StoreService storeService;

  @PostMapping
  public StoreResponse storeAdd(@RequestBody @Valid StoreRequest storeRequest) {

    return StoreResponse.of(storeService.addStore(storeRequest.getStoreName(),
        storeRequest.getRoadAddress(), storeRequest.getDetailAddress(), storeRequest.getDescription()));
  }

}
