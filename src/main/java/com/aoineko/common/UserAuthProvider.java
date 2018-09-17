package com.aoineko.common;

import com.aoineko.entity.SysUserDetail;
import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by aoineko on 2018/9/15.
 */

@Component
public class UserAuthProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger("user");
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    @Autowired
    UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
                : authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        User user = userService.validate(username, password);
        if (user == null) {
            throw new BadCredentialsException("user password error");
        }
        SysUserDetail sysUserDetail = new SysUserDetail(user);

        LOGGER.info(username, password);
        return createSuccessAuthentication(authentication.getPrincipal(), authentication, sysUserDetail);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }

    protected Authentication createSuccessAuthentication(Object principal,
                                                         Authentication authentication, UserDetails user) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details
        System.out.println(user.getAuthorities());
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                principal, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());

        return result;
    }
}
