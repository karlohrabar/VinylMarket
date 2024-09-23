package com.app.VinylMarket.controller;

import com.app.VinylMarket.dto.UserAuthDto;
import com.app.VinylMarket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new UserAuthDto());
        return "register";
    }

    @GetMapping("/admin")
    public String showAdminPage(){return "admin_page";}

    @GetMapping("/user")
    public String showUserPage(){
        return "user_page";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserAuthDto userAuthDto,
                               BindingResult result,
                               Model model){
        var existingUserByMail = userService.findUserByEmail(userAuthDto.getEmail());
        var existingUserByUsername = userService.findUserByUsername(userAuthDto.getUsername());

        if(existingUserByMail.isPresent())
            result.rejectValue("email", null, "There is already an account registered with the same email!");

        if(existingUserByUsername.isPresent())
            result.rejectValue("username", null, "There is already an account registered with the same username!");

        if(result.hasErrors()){
            model.addAttribute("user", userAuthDto);
            return "/register";
        }

        userService.saveUser(userAuthDto);
        System.out.println("Registering success!");
        return "redirect:/register?success";

    }
}
