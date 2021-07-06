package com.inventory.domain.repository;

import com.inventory.domain.model.Item;
import com.inventory.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
