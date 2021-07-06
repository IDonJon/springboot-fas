package com.inventory.controller;

import com.inventory.domain.model.Item;
import com.inventory.domain.service.ItemService;
import com.inventory.resource.ItemResource;
import com.inventory.resource.ItemSaveResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get item", description = "Get an item", tags = {"Item"})
    @GetMapping(path = "/items/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<Item> optionalItem = itemService.findById(id);
            if (optionalItem.isPresent()) {
                return new ResponseEntity<Item>(optionalItem.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create item", description = "Create a new item", tags = {"Item"})
    @PostMapping("/items")
    public ItemResource postItem(@Valid @RequestBody ItemSaveResource resource){
        Item item = convertToEntity(resource);
        return convertToResource(itemService.createItem(item));
    }

    private Item convertToEntity(ItemSaveResource resource) {
        return mapper.map(resource, Item.class);
    }

    private ItemResource convertToResource(Item entity) {
        return mapper.map(entity, ItemResource.class);
    }

    @Operation(summary = "Update item", description = "Update an item", tags = {"Item"})
    @PutMapping(value = "/items/{id}")
    public ResponseEntity<Item> update(@PathVariable("id") Long id, @RequestBody Item items) {

        try {
            Optional<Item> optionalItem = itemService.findById(id);
            if (optionalItem.isPresent()) {
                Item itemCreate = itemService.createItem(items);
                return ResponseEntity.status(HttpStatus.CREATED).body(itemCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete item", description = "Delete an item", tags = {"Item"})
    @DeleteMapping(value = "/items/{id}")
    public ResponseEntity<Item> deleteById(@PathVariable("id") Long id) {


        try {
            Optional<Item> optionalItem = itemService.findById(id);
            if (optionalItem.isPresent()) {
                itemService.deleteById(id);
                return new ResponseEntity<Item>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
