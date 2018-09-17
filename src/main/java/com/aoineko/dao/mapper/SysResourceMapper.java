package com.aoineko.dao.mapper;

import com.aoineko.entity.Post;
import com.aoineko.entity.SysResource;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

    @ResultType(List.class)
    @Select("select sys_resource_id from user_resource where user_id= #{id}")
    List<Long> getUserResIds(Long id);
}