package com.aoineko.dao;

import com.aoineko.entity.User;

public interface UserDAO {
    int addUser(User user);

    User getUserByNameAndPasswd(String name, String passwd);

    User getById(Long userId);

    User getUserByName(String userName);
}
