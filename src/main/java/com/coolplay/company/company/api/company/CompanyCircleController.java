package com.coolplay.company.company.api.company;

import com.coolplay.company.common.utils.PageConvertUtil;
import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.company.model.CompanyCircleModel;
import com.coolplay.company.company.service.ICompanyCircleService;
import com.coolplay.company.core.model.UserModel;
import com.coolplay.company.core.model.UserRoleModel;
import com.coolplay.company.security.security.CoolplayUserCache;
import com.coolplay.company.security.service.IRoleService;
import com.coolplay.company.security.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2019/9/22.
 */
@Controller
@RequestMapping("/api/company/companyCircle")
public class CompanyCircleController {

    @Autowired
    private ICompanyCircleService companyCircleService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map list(CompanyCircleModel companyCircleModel,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "15") int pageSize) {

        PageInfo<CompanyCircleModel> pageInfo = companyCircleService.selectByFilterAndPage(companyCircleModel, pageNum, pageSize);


        return PageConvertUtil.grid(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value="/getCompanyCircle", method = RequestMethod.GET)
    public Result getUser(@RequestParam("id") int id) {
        CompanyCircleModel companyCircleModel = companyCircleService.findById(id);

        return ResponseUtil.success(companyCircleModel);
    }

    @ResponseBody
    @RequestMapping(value="/updateCompanyCircle", method = RequestMethod.GET)
    public Result updateCompanyCircle(@RequestParam("id") int id, @RequestParam("status") int status, @RequestParam("rejectReason")String rejectReason) {
        CompanyCircleModel companyCircleModel = new CompanyCircleModel();
        companyCircleModel.setId(id);
        companyCircleModel.setReviewStatus(1);
        companyCircleModel.setStatus(status);
        companyCircleModel.setRejectReason(rejectReason);

        int updateCnt = companyCircleService.updateNotNull(companyCircleModel);

        return ResponseUtil.success();
    }

    @ResponseBody
    @RequestMapping(value="/updateCompanyCircleDisable", method = RequestMethod.GET)
    public Result updateCompanyCircleDisable(@RequestParam("id") int id, @RequestParam("is_disable") int isDisable, @RequestParam("disableReason")String disableReason) {
        CompanyCircleModel companyCircleModel = new CompanyCircleModel();
        companyCircleModel.setId(id);
        companyCircleModel.setIsDisable(isDisable);
        companyCircleModel.setDisableReason(disableReason);

        int updateCnt = companyCircleService.updateNotNull(companyCircleModel);
        return ResponseUtil.success();
    }

}
