package com.coolplay.company.core.dao;

import com.coolplay.company.core.model.RoleModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2019/9/16.
 */
public interface RoleMapper extends Mapper<RoleModel> {

    public List<Map> findRoleMatchUpFunctions();
}
