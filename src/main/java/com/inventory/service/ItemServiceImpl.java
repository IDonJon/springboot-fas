package com.inventory.service;

import com.inventory.domain.model.Item;
import com.inventory.domain.repository.ItemRepository;
import com.inventory.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long aLong){
        return itemRepository.findById(aLong);
    }


    @Override
    public void deleteById(Long aLong) {
        itemRepository.deleteById(aLong);
    }
}
