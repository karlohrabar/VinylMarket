package com.app.VinylMarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer{

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .formLogin(form -> form.loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/login")
                        .successHandler(myAuthenticationSuccessHandler()))
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/user/**").authenticated()
                                .requestMatchers("/admin/**").authenticated()
                                .requestMatchers("/item/**").authenticated()
                                .requestMatchers("/item-photos/**").authenticated()
                                .requestMatchers("/payment/**").authenticated()
                                .requestMatchers("/pendingOrders").authenticated()
                                .requestMatchers("/buyerOrders").authenticated()
                                .requestMatchers("/seller/**").authenticated())

                .logout((logout) -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll());




        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

}


