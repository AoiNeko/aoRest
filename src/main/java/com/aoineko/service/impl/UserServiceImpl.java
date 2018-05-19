package com.aoineko.service.impl;

import com.aoineko.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by com.aoineko on 2018/5/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean validate(String name, String passwd) {
        return false;
    }

    private String getJwt() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
        return null;
    }
}
