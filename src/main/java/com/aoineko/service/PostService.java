package com.aoineko.service;

import com.aoineko.dto.PostDTO;

import java.util.List;

/**
 * Created by com.aoineko on 2018/5/16.
 */
public interface PostService {
    PostDTO getPostDTO(Long aLong);

    PostDTO getPostContent(Long aLong);

    List<PostDTO> getDayList(Long dayTimestamp, Integer timeZone);

    List<PostDTO> getMonthList(Long dayTimestamp, Integer timeZone);
}
