package com.coolplay.company.security.service.impl;

import com.coolplay.company.common.baseservice.impl.BaseService;
import com.coolplay.company.company.model.CompanyDeptModel;
import com.coolplay.company.core.dao.RoleMapper;
import com.coolplay.company.core.model.RoleModel;
import com.coolplay.company.security.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public PageInfo<RoleModel> selectByFilterAndPage(RoleModel roleModel, int pageNum,
            int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoleModel> list = this.selectByFilter(roleModel);
        return new PageInfo<>(list);
    }

    @Override
    public List<RoleModel> selectByFilter(RoleModel roleModel) {
        Example example = new Example(RoleModel.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotEmpty(roleModel.getRoleName())) {
            criteria.andGreaterThanOrEqualTo("roleName", roleModel.getRoleName());
        }
        if(StringUtils.isNotEmpty(roleModel.getSortWithOutOrderBy())) {
            example.setOrderByClause(roleModel.getSortWithOutOrderBy());
        }
        return getMapper().selectByExample(example);
    }


    @Override
    public RoleModel selectById(int id) {
        return roleMapper.selectById(id);
    }
}
