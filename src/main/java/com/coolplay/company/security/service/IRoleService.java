package com.coolplay.company.security.service;

import com.coolplay.company.common.baseservice.IBaseService;
import com.coolplay.company.company.model.CompanyDeptModel;
import com.coolplay.company.core.model.RoleModel;
import com.coolplay.company.core.model.UserModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2019/9/16.
 */
public interface IRoleService extends IBaseService<RoleModel> {

    List<Map> findRoleMatchUpFunctions();

    public PageInfo<RoleModel> selectByFilterAndPage(RoleModel roleModel, int pageNum, int pageSize);

    public List<RoleModel> selectByFilter(RoleModel roleModel);

    public RoleModel selectById(int id);
}
