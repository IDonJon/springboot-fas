package com.inventory.domain.service;

import com.inventory.domain.model.Item;

import java.util.Optional;

public interface ItemService {
    Item createItem(Item item);
    Optional<Item> findById(Long aLong);
    void deleteById(Long aLong);
}
