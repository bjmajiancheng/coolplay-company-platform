package com.coolplay.company.security.service.impl;

import com.coolplay.company.common.baseservice.impl.BaseService;
import com.coolplay.company.core.dao.UserMapper;
import com.coolplay.company.core.model.User;
import com.coolplay.company.security.dto.FunctionDto;
import com.coolplay.company.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by majiancheng on 2019/9/16.
 */
public class UserServiceImpl extends BaseService<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据登录名获取用户信息
     *
     * @param loginName
     * @return
     */
    public User findUserByLoginName(String loginName) {
        return userMapper.findUserByLoginName(loginName);
    }

    @Override
    public List<Integer> findUserRoleByUserId(int userId) {
        return userMapper.findUserRoleByUserId(userId);
    }

    /**
     * 根据登录名获取用户权限信息
     *
     * @param loginName
     * @return
     */
    public List<FunctionDto> findUserFunctionByLoginName(String loginName) {
        return userMapper.findUserFunctionByLoginName(loginName);
    }

    /**
     * 根据用户ID获取登录名
     *
     * @param userId
     * @return
     */
    public String findLoginNameByUserId(Integer userId) {
        return userMapper.findLoginNameByUserId(userId);
    }
}