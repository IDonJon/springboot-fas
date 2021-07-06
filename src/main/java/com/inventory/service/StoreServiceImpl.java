package com.inventory.service;


import com.inventory.domain.model.Store;
import com.inventory.domain.repository.StoreRepository;
import com.inventory.domain.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Optional<Store> findById(Long aLong){
        return storeRepository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        storeRepository.deleteById(aLong);
    }
}
