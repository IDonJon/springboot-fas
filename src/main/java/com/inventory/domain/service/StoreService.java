package com.inventory.domain.service;

import com.inventory.domain.model.Store;

import java.util.Optional;

public interface StoreService {
    Store createStore(Store store);
    Optional<Store> findById(Long aLong);
    void deleteById(Long aLong);
}
