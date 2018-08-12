package com.aoineko.dao;

/**
 * Created by aoineko on 2018/8/13.
 */
public interface LoginTokenDAO {
    Long getUserIdByToken(String key);
}
