package com.aoineko.dao;

import com.aoineko.entity.Post;

import java.util.Date;
import java.util.List;

/**
 * Created by aoineko on 2018/6/11.
 */
public interface PostDAO {
    Post getPostById(Long id);

    List<Post> getPostByDate(Date date, String timeZone);
}
