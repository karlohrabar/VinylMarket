package com.app.VinylMarket.controller;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @GetMapping("/create")
    public String showCreationForm(Model model){

        model.addAttribute("item", new ItemDto());
        return "item_creation";
    }

    @PostMapping("/create")
    public String submitItem(@Valid @ModelAttribute("item") ItemDto itemDto,
                             BindingResult result,
                             Model model){
        itemService.saveItem(itemDto);
        return "redirect:/create?success";
    }
}
