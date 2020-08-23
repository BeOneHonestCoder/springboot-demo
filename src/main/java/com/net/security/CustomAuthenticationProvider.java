
package com.net.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OAuth2RestTemplate oauth2RestTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(isValidToken()){
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        }
        throw new IllegalArgumentException("Authentication FAILED!!!, Token is invalid or expired!!!");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isValidToken(){
        OAuth2AccessToken token = oauth2RestTemplate.getAccessToken();
        return !token.isExpired();
    }

}

