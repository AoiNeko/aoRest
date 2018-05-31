package com.aoineko.dao.impl;

import com.aoineko.dao.UserDAO;
import com.aoineko.dao.mapper.UserMapper;
import com.aoineko.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUserByNameAndPasswd(String name, String passwd) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", name).andEqualTo("password", passwd);
        return userMapper.selectOneByExample(example);
    }
}
