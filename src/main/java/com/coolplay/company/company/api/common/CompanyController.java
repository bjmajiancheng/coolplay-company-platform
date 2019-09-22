package com.coolplay.company.company.api.common;

import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.company.model.CompanyModel;
import com.coolplay.company.company.service.ICompanyService;
import com.coolplay.company.security.security.SecurityUser;
import com.coolplay.company.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2019/9/19.
 */
@Controller
@RequestMapping("/api/common/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @ResponseBody
    @RequestMapping(value="/companyInfo", method = RequestMethod.GET)
    public Result companyInfo(HttpServletRequest request) {
        SecurityUser securityUser = SecurityUtil.getCurrentSecurityUser();

        CompanyModel companyModel = companyService.findCompanyById(securityUser.getCompanyId());

        return ResponseUtil.success(companyModel);
    }

    @ResponseBody
    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    public Result updateCompany(CompanyModel companyModel) {
        int cnt = companyService.updateNotNull(companyModel);

        return ResponseUtil.success();
    }


}
