package com.app.VinylMarket.controller;

import com.app.VinylMarket.dto.UserInfoDto;
import com.app.VinylMarket.dto.UserLoginDto;
import com.app.VinylMarket.entities.UserEntity;
import com.app.VinylMarket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserLoginDto user = new UserLoginDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("register/save")
    public String registration(@Valid @ModelAttribute("user") UserLoginDto userLoginDto,
                               BindingResult result,
                               Model model){
        var existingUserByMail = userService.findUserByEmail(userLoginDto.getEmail());
        var existingUserByUsername = userService.findUserByUsername(userLoginDto.getUsername());

        if(existingUserByMail.isPresent())
            result.rejectValue("email", null, "There is already an account registered with the same email!");

        if(existingUserByUsername.isPresent())
            result.rejectValue("username", null, "There is already an account registered with the same username!");

        if(result.hasErrors()){
            model.addAttribute("user", userLoginDto);
            return "/register";
        }

        userService.saveUser(userLoginDto);
        return "redirect:/register?success";

    }
}
