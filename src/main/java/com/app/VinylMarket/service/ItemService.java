package com.app.VinylMarket.service;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.mappers.ItemMapper;
import com.app.VinylMarket.mappers.ItemMapperImpl;
import com.app.VinylMarket.repository.ItemRepository;
import com.app.VinylMarket.repository.UserRepository;
import com.app.VinylMarket.security.userInfo.AuthenticatonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private AuthenticatonFacade authenticatonFacade;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;
    public void saveItem(ItemDto itemDto){

        var username = authenticatonFacade.getAuthentication().getName();
        var user = userRepository.findByUsername(username);

        if(user.isPresent()){
            var mapper = new ItemMapperImpl();
            var item = mapper.toEntity(itemDto);
            item.setUserEntity(user.get());
            itemRepository.save(item);
        }

    }

}
