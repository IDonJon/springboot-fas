package com.inventory.controller;



import com.inventory.domain.model.Store;
import com.inventory.domain.service.StoreService;
import com.inventory.resource.StoreResource;
import com.inventory.resource.StoreSaveResource;
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
public class StoreController {

    @Autowired
    private ModelMapper mapperstore;

    @Autowired
    private StoreService storeService;

    @Operation(summary = "Get store", description = "Get a store", tags = {"Store"})
    @GetMapping(path = "/stores/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<Store> optionalStore = storeService.findById(id);
            if (optionalStore.isPresent()) {
                return new ResponseEntity<Store>(optionalStore.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create item", description = "Create a new item", tags = {"Store"})
    @PostMapping("/stores")
    public StoreResource postStore(@Valid @RequestBody StoreSaveResource resource){
        Store store = convertToEntity(resource);
        return convertToResource(storeService.createStore(store));
    }

    private Store convertToEntity(StoreSaveResource resource) {
        return mapperstore.map(resource, Store.class);
    }

    private StoreResource convertToResource(Store entity) {
        return mapperstore.map(entity, StoreResource.class);
    }

    @Operation(summary = "Update Store", description = "Update a store", tags = {"Store"})
    @PutMapping(value = "/stores/{id}")
    public ResponseEntity<Store> update(@PathVariable("id") Long id, @RequestBody Store stores) {

        try {
            Optional<Store> optionalStore = storeService.findById(id);
            if (optionalStore.isPresent()) {
                Store storeCreate = storeService.createStore(stores);
                return ResponseEntity.status(HttpStatus.CREATED).body(storeCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete store", description = "Delete a store", tags = {"Store"})
    @DeleteMapping(value = "stores/{id}")
    public ResponseEntity<Store> deleteById(@PathVariable("id") Long id) {


        try {
            Optional<Store> optionalStore = storeService.findById(id);
            if (optionalStore.isPresent()) {
                storeService.deleteById(id);
                return new ResponseEntity<Store>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
