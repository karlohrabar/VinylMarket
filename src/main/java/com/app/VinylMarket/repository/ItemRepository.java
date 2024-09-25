package com.app.VinylMarket.repository;

import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

    List<ItemEntity> findAll();
    List<ItemEntity> findByItemStatus(ItemStatus status);

    @Query("SELECT * FROM Item i WHERE i.user_id = :id")
    List<ItemEntity> findByUser(@Param("id") UUID id);
}
