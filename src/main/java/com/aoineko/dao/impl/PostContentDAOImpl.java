package com.aoineko.dao.impl;

import com.aoineko.dao.PostContentDAO;
import com.aoineko.dao.mapper.PostContentMapper;
import com.aoineko.entity.PostContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

@Repository
public class PostContentDAOImpl implements PostContentDAO {
    @Autowired
    private PostContentMapper postContentMapper;
    @Override
    public PostContent getContentById(Long aLong) {
        Example example =new Example(PostContent.class);
        example.createCriteria().andEqualTo("postId", aLong);
        return postContentMapper.selectOneByExample(example);
    }
}
