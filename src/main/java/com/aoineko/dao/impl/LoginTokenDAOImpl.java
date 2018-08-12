package com.aoineko.dao.impl;

import com.aoineko.dao.LoginTokenDAO;
import com.aoineko.dao.mapper.LoginTokenMapper;
import com.aoineko.entity.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by aoineko on 2018/8/13.
 */

@Repository
public class LoginTokenDAOImpl implements LoginTokenDAO {

    @Autowired
    private LoginTokenMapper loginTokenMapper;

    @Override
    public Long getUserIdByToken(String key) {
        Example example = new Example(LoginToken.class);
        example.createCriteria().andEqualTo("token", key).andEqualTo("deleted", 0);
        List<LoginToken> lists = loginTokenMapper.selectByExample(example);
        return lists != null && lists.size() > 0 ? lists.get(0).getUserId() : null;
    }
}
