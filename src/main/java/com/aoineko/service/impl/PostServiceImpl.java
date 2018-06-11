package com.aoineko.service.impl;

import com.aoineko.dao.PostDAO;
import com.aoineko.entity.Post;
import com.aoineko.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by com.aoineko on 2018/5/16.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    @Override
    public Post getPost(Long aLong) {
        return postDAO.getPostById(aLong);
    }
}
;