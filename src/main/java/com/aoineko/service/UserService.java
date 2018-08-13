package com.aoineko.service;

import com.aoineko.entity.User;

import java.util.concurrent.ExecutionException;

/**
 * Created by com.aoineko on 2018/5/15.
 */
public interface UserService {
    User validate(String name, String passwd);

    String genJWT(User user);

    int addUser(User user);

    boolean authVerify(String jwtString);

    Long getTokenUserId(String key) throws ExecutionException;

    void addUserLoginToken(String jwt, User user);
}
