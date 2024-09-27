package com.app.VinylMarket.controller;


import com.app.VinylMarket.dto.OrderDto;
import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.security.CustomUserDetails;
import com.app.VinylMarket.security.userInfo.AuthenticationFacade;
import com.app.VinylMarket.service.ItemService;
import com.app.VinylMarket.service.OrderService;
import com.app.VinylMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class OrderController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/item/details/{id}")
    public String getItemDetails(@PathVariable("id") UUID itemId, Model model) {
        Optional<ItemEntity> item = itemService.findById(itemId);
        if (item.isEmpty()) {
            return "redirect:/item/allInStock?error=ItemNotFound";
        }
        model.addAttribute("item", item.get());
        return "payment";
    }

    @PostMapping("/payment/process/{itemId}")
    public String processPayment(@PathVariable("itemId") UUID itemId) {
        System.out.println("entering func");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        System.out.println("creating order");
        // Create order for the selected item
        orderService.createOrder(itemId, userDetails.getId());

        System.out.println("confirmation");
        return "confirmation";
    }

    @GetMapping("/pendingOrders")
    public String getSellerOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UUID sellerId = userDetails.getId();
        List<OrderDto> orders = orderService.getAllSellerOrders(sellerId);  // Get all seller orders (pending, approved, rejected)
        model.addAttribute("orders", orders);
        return "mySellerOrders";  // Thymeleaf page to display all orders
    }

    @GetMapping("/buyerOrders")
    public String getBuyerOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID buyerId = userDetails.getId();

        // Fetch all orders for the buyer
        List<OrderDto> orders = orderService.getAllBuyerOrders(buyerId);

        model.addAttribute("orders", orders);
        return "myBuyerOrders";  // Thymeleaf template to show the orders
    }

    @PostMapping("seller/approve/{id}")
    public String approveOrder(@PathVariable UUID id) {
        orderService.approveOrder(id);
        return "redirect:/pendingOrders";
    }

    @PostMapping("seller/reject/{id}")
    public String rejectOrder(@PathVariable UUID id) {
        orderService.rejectOrder(id);
        return "redirect:/pendingOrders";
    }


}
