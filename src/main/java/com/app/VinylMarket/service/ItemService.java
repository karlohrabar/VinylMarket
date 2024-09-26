package com.app.VinylMarket.service;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.enums.ItemStatus;
import com.app.VinylMarket.mappers.ItemMapperImpl;
import com.app.VinylMarket.repository.ItemRepository;
import com.app.VinylMarket.repository.UserRepository;
import com.app.VinylMarket.security.CustomUserDetails;
import com.app.VinylMarket.security.userInfo.AuthenticationFacade;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ItemService {

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public void changeItemStatusToSold(UUID id){
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        item.setItemStatus(ItemStatus.SOLD);
        itemRepository.save(item);
    }

    public Optional<ItemEntity> findById(UUID id){
        return itemRepository.findById(id);
    }

    public void saveItem(ItemDto itemDto){

        var username = authenticationFacade.getAuthentication().getName();
        var user = userRepository.findByUsername(username);

        if(user.isPresent()){
            var mapper = new ItemMapperImpl();
            var item = mapper.toEntity(itemDto);
            item.setUserEntity(user.get());
            item.setItemStatus(ItemStatus.IN_STOCK);
            itemRepository.save(item);
        }

    }

    public List<ItemDto> getItemsByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        var currentUser = userRepository.findById(userDetails.getId()).orElseThrow(()-> new UsernameNotFoundException("User not found!"));

        var mapper = new ItemMapperImpl();
        return itemRepository.findByUser(currentUser).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }

    public List<ItemDto> getItemsNotByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        var currentUser = userRepository.findById(userDetails.getId()).orElseThrow(()-> new UsernameNotFoundException("User not found!"));

        var mapper = new ItemMapperImpl();
        return itemRepository.findInStockItemsNotByUser(currentUser).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }



    public String generateUniqueFileName(String uploadDir, String fileName) {
        String extension = "";
        String baseName = fileName;

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex);
            baseName = fileName.substring(0, dotIndex);
        }
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        return baseName + "_" + timestamp + extension;
    }

    public List<ItemEntity> getAllEntities() {
        return itemRepository.findAll();
    }
}
