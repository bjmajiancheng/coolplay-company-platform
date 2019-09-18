package com.coolplay.company.security.service;

import com.coolplay.company.common.baseservice.IBaseService;
import com.coolplay.company.core.model.CompanyRoleModel;

import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2019/9/16.
 */
public interface IRoleService extends IBaseService<CompanyRoleModel> {

    List<Map> findRoleMatchUpFunctions();
}
