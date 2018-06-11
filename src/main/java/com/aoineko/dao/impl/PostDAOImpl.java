package com.aoineko.dao.impl;

import com.aoineko.dao.PostDAO;
import com.aoineko.dao.mapper.PostMapper;
import com.aoineko.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by aoineko on 2018/6/11.
 */
@Repository
public class PostDAOImpl implements PostDAO {

    @Autowired
    private PostMapper postMapper;

    @Override
    public Post getPostById(Long id) {
        return postMapper.selectByPrimaryKey(id);
    }
}
