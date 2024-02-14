package com.app.VinylMarket.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());
    
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        handle(request,response,authentication);
        clearAuthenticationAttributes(request);
        
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null){
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()){
            logger.debug("Response has already been commited. Unable to connect to "+
                    targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    private String determineTargetUrl(final Authentication authentication) {
        Map<String,String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("USER","/user");
        roleTargetUrlMap.put("ADMIN","/admin");

        final Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();
        for(final GrantedAuthority grantedAuthority:authorities){
            String authorityName = grantedAuthority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)){
                return roleTargetUrlMap.get(authorityName);
            }
        }
        throw new IllegalStateException();
    }
}
