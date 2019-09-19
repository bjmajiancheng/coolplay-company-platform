package com.coolplay.company.company.api.common;

import com.coolplay.company.common.utils.PageConvertUtil;
import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.core.model.UserModel;
import com.coolplay.company.security.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2019/9/19.
 */
@Controller
@RequestMapping("/api/common/companyUser")
public class CompanyUserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(UserModel userModel,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {

        PageInfo<UserModel> pageInfo = userService.selectByFilterAndPage(userModel, pageNum, pageSize);


        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @ResponseBody
    @RequestMapping(value="getUser", method = RequestMethod.GET)
    public Result getUser(@RequestParam("userId") int userId) {
        UserModel userModel = userService.findUserByUserId(userId);

        return ResponseUtil.success(userModel);
    }

    @ResponseBody
    @RequestMapping(value="updateUserEnabled", method = RequestMethod.POST)
    public Result updateUserEnabled(@RequestParam("userId") int userId, @RequestParam("enabled") int enabled) {
        UserModel userModel = new UserModel();
        userModel.setId(userId);
        userModel.setEnabled(enabled);
        int updateCnt = userService.updateNotNull(userModel);

        return ResponseUtil.success();
    }

    @ResponseBody
    @RequestMapping(value="updateUser", method = RequestMethod.POST)
    public Result updateUser(UserModel userModel) {
        int updateCnt = userService.updateNotNull(userModel);
        return ResponseUtil.success();
    }


}
