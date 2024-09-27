package com.app.VinylMarket.controller;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.security.userInfo.AuthenticationFacade;
import com.app.VinylMarket.service.ItemService;
import com.app.VinylMarket.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @GetMapping("/create")
    public String showCreationForm(Model model){

        model.addAttribute("item", new ItemDto());
        return "item_creation";
    }

    @PostMapping("/createItem")
    public String submitItem(@Valid @ModelAttribute("item") ItemDto itemDto,
                             BindingResult result,
                             Model model,
                             @RequestParam("photo") MultipartFile multipartFile) throws IOException {


        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        String uploadDir = "item-photos/";
        String uniqueFilename = itemService.generateUniqueFileName(uploadDir, fileName);

        itemDto.setPhoto(uniqueFilename);

        itemService.saveItem(itemDto);

        FileUploadUtil.saveFile(uploadDir, uniqueFilename, multipartFile);

        return "redirect:/item/create?success";
    }


    @GetMapping("/myItems")
    public String showMyItems(Model model){
        model.addAttribute("items", itemService.getItemsByCurrentUser());
        return "my_items";
    }

    @GetMapping("/marketplace")
    public String showMarketplace(Model model){
        model.addAttribute("items", itemService.getItemsNotByCurrentUser());
        return "marketplace";
    }


    @PostMapping("/sell/{id}")
    public String sellItem(@PathVariable UUID id){
        System.out.println("Selling item with ID: " + id);
        itemService.changeItemStatusToSold(id);
        return "redirect:/item/myItems";
    }


}
