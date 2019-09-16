package com.coolplay.company.security.service;

import com.coolplay.company.security.security.SecurityUser;

import java.util.List;

/**
 * Created by majiancheng on 2019/9/16.
 */
public interface ISecurityService {

    /**
     * 根据登录名称获取用户信息
     *
     * @param loginName
     * @return
     */
    public SecurityUser loadSecurityUserByLoginName(String loginName);
}
