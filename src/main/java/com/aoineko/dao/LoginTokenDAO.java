package com.aoineko.dao;

import com.aoineko.entity.LoginToken;

/**
 * Created by aoineko on 2018/8/13.
 */
public interface LoginTokenDAO {
    Long getUserIdByToken(String key);

    void saveLoginToken(LoginToken loginToken);
}
