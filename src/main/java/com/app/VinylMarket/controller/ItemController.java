package com.app.VinylMarket.controller;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.entities.UserEntity;
import com.app.VinylMarket.security.userInfo.AuthenticatonFacade;
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
import java.util.Objects;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthenticatonFacade authenticatonFacade;
    @GetMapping("/create")
    public String showCreationForm(Model model){

        model.addAttribute("item", new ItemDto());
        return "item_creation";
    }

    @PostMapping("/create")
    public String submitItem(@Valid @ModelAttribute("item") ItemDto itemDto,
                             BindingResult result,
                             Model model,
                             @RequestParam("photo") MultipartFile multipartFile) throws IOException {


        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        itemDto.setPhoto(fileName);
        itemService.saveItem(itemDto);

        String uploadDir = "item-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/create?success";
    }


    @GetMapping("/allItems")
    public String showAllItems(Model model){
        model.addAttribute("items", itemService.getAllDtos());
        return "all_items_page";
    }
}
