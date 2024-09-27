package com.app.VinylMarket.repository;

import com.app.VinylMarket.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("SELECT o FROM OrderEntity o WHERE o.item.userEntity.id = :sellerId")
    List<OrderEntity> findBySeller(@Param("sellerId") UUID sellerId);

    @Query("SELECT o FROM OrderEntity o WHERE o.buyer.id = :buyerId")
    List<OrderEntity> findByBuyer(@Param("buyerId") UUID buyerId);

}