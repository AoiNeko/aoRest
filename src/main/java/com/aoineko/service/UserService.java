package com.aoineko.service;

import com.aoineko.entity.User;

/**
 * Created by com.aoineko on 2018/5/15.
 */
public interface UserService {
    User validate(String name, String passwd);

    String genJWT(User user);

    int addUser(User user);

    boolean authVerify(String jwtString);

    Long getTokenUserId(String key);

    User getUserById(Long userId);
}
