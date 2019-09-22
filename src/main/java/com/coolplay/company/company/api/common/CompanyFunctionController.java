package com.coolplay.company.company.api.common;

import com.coolplay.company.common.utils.PageConvertUtil;
import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.company.model.CompanyDeptModel;
import com.coolplay.company.core.model.FunctionModel;
import com.coolplay.company.security.service.IFunctionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by majiancheng on 2019/9/19.
 */
@Controller
@RequestMapping("/api/common/companyFunction")
public class CompanyFunctionController {

    @Autowired
    private IFunctionService functionService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map list(FunctionModel functionModel,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "15") int pageSize) {
        PageInfo<FunctionModel> pageInfo = functionService
                .selectByFilterAndPage(functionModel, pageNum, pageSize);

        return PageConvertUtil.grid(pageInfo);
    }


    /**
     * 获取公司部门信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCompanyFunction", method = RequestMethod.GET)
    public Result getCompanyFunction(@RequestParam("id") int id) {
        FunctionModel functionModel = functionService.selectById(id);

        return ResponseUtil.success(functionModel);
    }


    /**
     * 添加公司部门信息
     *
     * @param functionModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCompanyFunction", method = RequestMethod.POST)
    public Result addCompanyFunction(FunctionModel functionModel) {
        int addCnt = functionService.save(functionModel);

        return ResponseUtil.success();
    }

    /**
     * 添加公司部门信息
     *
     * @param functionModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCompanyFunction", method = RequestMethod.POST)
    public Result updateCompanyFunction(FunctionModel functionModel) {
        int updateCnt = functionService.updateNotNull(functionModel);

        return ResponseUtil.success();
    }

    /**
     * 禁用或启用公司部门
     *
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/disableCompanyFunction", method = RequestMethod.GET)
    public Result disableCompanyFunction(@RequestParam("id") int id, @RequestParam("status") int status) {
        FunctionModel functionModel = new FunctionModel();
        functionModel.setId(id);
        functionModel.setStatus(status);
        int updateCnt = functionService.updateNotNull(functionModel);

        return ResponseUtil.success();
    }

    @ResponseBody
    @RequestMapping(value = "/treeNodes", method = RequestMethod.POST)
    public Object treeNodes(FunctionModel functionModel) {
        return functionService.getFunctionTreeNodes(functionModel);
    }

}