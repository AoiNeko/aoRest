package com.aoineko.service.impl;

import com.aoineko.common.util.DateUtils;
import com.aoineko.dao.PostContentDAO;
import com.aoineko.dao.PostDAO;
import com.aoineko.dto.PostDTO;
import com.aoineko.entity.Post;
import com.aoineko.entity.PostContent;
import com.aoineko.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
    public List<PostDTO> getDayList(Long dayTimestamp, Integer timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(DateUtils.getTimezone(timeZone)));
        calendar.setTimeInMillis(dayTimestamp);
        Date date =  calendar.getTime();
        List<Post> posts = postDAO.getPostByDate(date, DateUtils.getTimezoneForConvert(timeZone) );
        return posts.stream().map(post -> new PostDTO(post)).
                collect(Collectors.toList());
    }
}
