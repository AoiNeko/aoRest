package com.aoineko.dao.impl;

import com.aoineko.dao.SysResourceDAO;
import com.aoineko.dao.mapper.SysResourceMapper;
import com.aoineko.entity.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by aoineko on 2018/9/17.
 */
@Repository
public class SysResourceDAOImpl implements SysResourceDAO {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> getMenuByUser(List<Long> ids) {
        Example example = new Example(SysResource.class);
        example.createCriteria().andIn("id", ids);
        return sysResourceMapper.selectByExample(example);
    }

    @Override
    public List<Long> getUserResIds(Long id) {
        return sysResourceMapper.getUserResIds(id);
    }
}
