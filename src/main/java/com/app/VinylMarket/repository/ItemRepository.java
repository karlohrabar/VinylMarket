package com.app.VinylMarket.repository;

import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

    List<ItemEntity> findAll();


    @Query("SELECT i FROM ItemEntity i WHERE i.userEntity = :user")
    List<ItemEntity> findByUser(@Param("user")UserEntity user);

    @Query("SELECT i FROM ItemEntity i WHERE i.userEntity != :user AND i.itemStatus = 'IN_STOCK'")
    List<ItemEntity> findInStockItemsNotByUser(@Param("user")UserEntity user);
}
