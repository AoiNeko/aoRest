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
    public List<PostDTO> getDayList(Long dayTimestamp) {

//        dayTimestamp / 1000 / 60 / 60 / 24
        java.util.Date date = java.util.Date.from(Instant.ofEpochMilli(dayTimestamp).atZone(ZoneId.of("GMT")).toInstant());
        




        return null;
    }
}
