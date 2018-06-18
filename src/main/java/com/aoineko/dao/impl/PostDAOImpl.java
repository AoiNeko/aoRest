package com.aoineko.dao.impl;

import com.aoineko.dao.PostDAO;
import com.aoineko.dao.mapper.PostMapper;
import com.aoineko.entity.Post;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<Post> getPostByDate(Date date, String timeZone) {
        Example example = new Example(Post.class);

        example.createCriteria().andCondition("DATE_FORMAT(CONVERT_TZ(gmt_create,'+00:00', '" + timeZone + "' ) , '%Y-%m-%d') = '" + DateFormatUtils.format(date, "yyyy-MM-dd")  + "'");

        return postMapper.selectByExample(example);

    }
}
