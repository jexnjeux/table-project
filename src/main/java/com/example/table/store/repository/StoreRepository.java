package com.example.table.store.repository;

import com.example.table.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
  int countByStoreNameAndRoadAddress(String storeName, String roadAddress);

}
