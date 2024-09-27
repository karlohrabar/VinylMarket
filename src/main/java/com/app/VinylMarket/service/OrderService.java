package com.app.VinylMarket.service;

import com.app.VinylMarket.dto.OrderDto;
import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.entities.OrderEntity;
import com.app.VinylMarket.entities.UserEntity;
import com.app.VinylMarket.enums.ItemStatus;
import com.app.VinylMarket.enums.OrderStatus;
import com.app.VinylMarket.mappers.OrderMapper;
import com.app.VinylMarket.repository.ItemRepository;
import com.app.VinylMarket.repository.OrderRepository;
import com.app.VinylMarket.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository; // Ensure you have this to fetch UserEntity


    public void createOrder(UUID itemId, UUID buyerId) {
        // Create a new OrderEntity for the item
        OrderEntity order = new OrderEntity();

        // Fetch the item to set it in the order
        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found")); // Set item
        order.setItem(item);

        UserEntity seller = item.getUserEntity(); // Assuming you have a method to get the seller
        order.setSeller(seller); // Set the seller
        order.setBuyer(userRepository.findById(buyerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"))); // Set buyer
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);

        item.setItemStatus(ItemStatus.PENDING); // Assuming you have a method to set item status
        itemRepository.save(item);
    }

    public List<OrderDto> getAllSellerOrders(UUID sellerId) {
        return orderRepository.findBySeller(sellerId)
                .stream()
                .map(OrderMapper::toDto)  // Convert each OrderEntity to OrderDto
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllBuyerOrders(UUID buyerId) {
        return orderRepository.findByBuyer(buyerId)
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public void approveOrder(UUID orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
        order.setStatus(OrderStatus.APPROVED);
        order.getItem().setItemStatus(ItemStatus.SOLD);
        orderRepository.save(order);
    }

    public void rejectOrder(UUID orderID){
        OrderEntity order = orderRepository.findById(orderID).orElseThrow(()->new EntityNotFoundException("Order not found!"));
        order.setStatus(OrderStatus.REJECTED);
        order.getItem().setItemStatus(ItemStatus.IN_STOCK);
        orderRepository.save(order);
    }

}
