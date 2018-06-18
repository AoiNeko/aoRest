package com.aoineko.service.impl;

import com.aoineko.dao.PostContentDAO;
import com.aoineko.dao.PostDAO;
import com.aoineko.dto.PostDTO;
import com.aoineko.entity.Post;
import com.aoineko.entity.PostContent;
import com.aoineko.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.calendar.ZoneInfo;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by com.aoineko on 2018/5/16.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PostContentDAO postContentDAO;

    @Override
    public PostDTO getPostDTO(Long aLong) {
        Post post = postDAO.getPostById(aLong);
        return new PostDTO(post);
    }


    @Override
    public PostDTO getPostContent(Long aLong) {
        PostContent postContent = postContentDAO.getContentById(aLong);
        return new PostDTO(postContent);
    }

    @Override
    public List<PostDTO> getDayList(Long dayTimestamp, String timeZone) {
        java.util.Date date = java.util.Date.from(Instant.ofEpochMilli(dayTimestamp));
        List<Post> posts = postDAO.getPostByDate(date, timeZone);
        return posts.stream().map(post -> new PostDTO(post)).
                collect(Collectors.toList());
    }
}
