package com.coolplay.company.core.api;

import com.coolplay.company.common.tools.RedisCache;
import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.security.constants.SecurityConstant;
import com.coolplay.company.security.dto.FunctionDto;
import com.coolplay.company.security.exception.AuBzConstant;
import com.coolplay.company.security.exception.AuthBusinessException;
import com.coolplay.company.security.security.CoolplayUserCache;
import com.coolplay.company.security.service.IFunctionService;
import com.coolplay.company.security.service.IUserService;
import com.coolplay.company.security.utils.SecurityUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by majiancheng on 2019/9/18.
 */
@Controller
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IFunctionService functionService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private CoolplayUserCache coolplayUserCache;


    @ResponseBody
    @RequestMapping(value = "/index/current", method = RequestMethod.GET)
    public Result myFunction() {
        String currentLoginName = SecurityUtil.getCurrentUserName();
        if (StringUtils.isEmpty(currentLoginName)) {
            throw new AuthBusinessException(AuBzConstant.IS_NOT_LOGIN);
        }
        List<FunctionDto> function = (List<FunctionDto>) redisCache
                .get(SecurityConstant.FUNCTION_CACHE_PREFIX + currentLoginName);
        if (function == null) {
            function = userService.findUserFunctionByLoginName(currentLoginName);
            redisCache.set(SecurityConstant.FUNCTION_CACHE_PREFIX + currentLoginName, function);
        }
        return ResponseUtil.success(function);
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result authenticationRequest() {
        Integer userId = SecurityUtil.getCurrentUserId();
        coolplayUserCache.removeUserFromCacheByUserId(userId);
        return ResponseUtil.success();
    }

}
