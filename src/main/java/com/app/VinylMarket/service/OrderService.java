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
    private UserRepository userRepository;


    public void createOrder(UUID itemId, UUID buyerId) {
        OrderEntity order = new OrderEntity();

        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        order.setItem(item);

        UserEntity seller = item.getUserEntity();
        order.setSeller(seller);
        order.setBuyer(userRepository.findById(buyerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);

        item.setItemStatus(ItemStatus.PENDING);
        itemRepository.save(item);
    }

    public List<OrderDto> getAllSellerOrders(UUID sellerId) {
        return orderRepository.findBySeller(sellerId)
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllBuyerOrders(UUID buyerId) {
        return orderRepository.findByBuyer(buyerId)
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public void approveOrder(UUID orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
        order.setStatus(OrderStatus.APPROVED);
        order.getItem().setItemStatus(ItemStatus.SOLD);
        orderRepository.save(order);
    }

    public void rejectOrder(UUID orderID){
        var order = orderRepository.findById(orderID).orElseThrow(()->new EntityNotFoundException("Order not found!"));
        order.setStatus(OrderStatus.REJECTED);
        order.getItem().setItemStatus(ItemStatus.IN_STOCK);
        orderRepository.save(order);
    }

}
