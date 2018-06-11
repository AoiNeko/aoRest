package com.aoineko.dao;

import com.aoineko.entity.Post;

/**
 * Created by aoineko on 2018/6/11.
 */
public interface PostDAO {
    Post getPostById(Long id);
}
