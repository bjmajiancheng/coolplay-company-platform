package com.coolplay.company.core.dao;

import com.coolplay.company.core.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by majiancheng on 2019/9/16.
 */
public interface UserMapper extends Mapper<User> {

    User findUserByLoginName(@Param("loginName") String loginName);

    List<Integer> findUserRoleByUserId(@Param("userId")int userId);
}
