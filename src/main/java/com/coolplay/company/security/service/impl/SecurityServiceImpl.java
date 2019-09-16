package com.coolplay.company.security.service.impl;

import com.coolplay.company.core.model.User;
import com.coolplay.company.security.security.SecurityUser;
import com.coolplay.company.security.service.ISecurityService;
import com.coolplay.company.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by majiancheng on 2019/9/16.
 */
public class SecurityServiceImpl implements ISecurityService {

    @Autowired
    private IUserService userService;
    /**
     * 根据登录名称获取用户信息
     *
     * @param loginName
     * @return
     */
    public SecurityUser loadSecurityUserByLoginName(String loginName) {
        User user = userService.findUserByLoginName(loginName);
        if (user == null) {
            return null;
        }
        Collection<GrantedAuthority> userGrantedAuthorities = new ArrayList<GrantedAuthority>();
        List<Integer> grantedAuthorities = userService.findUserRoleByUserId(user.getId());
        if (grantedAuthorities != null && grantedAuthorities.size() > 0) {
            for (Integer grantedAuthority : grantedAuthorities) {
                GrantedAuthority ga = new SimpleGrantedAuthority(String.valueOf(grantedAuthority));
                userGrantedAuthorities.add(ga);
            }
        }
        SecurityUser securityUser = new SecurityUser(user, userGrantedAuthorities);
        return securityUser;
    }
}
