package com.coolplay.company.security.service.impl;

import com.coolplay.company.common.baseservice.impl.BaseService;
import com.coolplay.company.core.dao.RoleMapper;
import com.coolplay.company.core.model.RoleModel;
import com.coolplay.company.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2019/9/16.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService<RoleModel> implements IRoleService{


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Map> findRoleMatchUpFunctions() {
        return roleMapper.findRoleMatchUpFunctions();
    }
}
