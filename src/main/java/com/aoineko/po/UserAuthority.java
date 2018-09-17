package com.aoineko.po;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by aoineko on 2018/9/16.
 */
public class UserAuthority implements GrantedAuthority{

    private final String role ="USER_READ";
    @Override
    public String getAuthority() {
        return role;
    }
}
