package com.aoineko.dao;

import com.aoineko.entity.SysResource;

import java.util.List;

/**
 * Created by aoineko on 2018/9/17.
 */
public interface SysResourceDAO {
    List<SysResource> getMenuByUser(List<Long> id);

    List<Long> getUserResIds(Long id);

}
