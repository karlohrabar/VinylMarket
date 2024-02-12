package com.app.VinylMarket.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto
{
    @NotEmpty(message = "User Name field is empty!")
    private String username;

    @NotEmpty(message = "Email field is empty!")
    private String email;

    @NotEmpty(message = "Password field is empty!")
    private String passwd;

    @NotEmpty(message = "Country field is empty!")
    private String country;

}