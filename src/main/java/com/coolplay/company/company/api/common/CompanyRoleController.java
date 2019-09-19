package com.coolplay.company.company.api.common;

import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.company.model.CompanyDeptModel;
import com.coolplay.company.core.model.RoleModel;
import com.coolplay.company.security.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by majiancheng on 2019/9/19.
 */
@Controller
@RequestMapping("/api/common/companyRole")
public class CompanyRoleController {

    @Autowired
    private IRoleService roleService;

    @ResponseBody
    @RequestMapping("list")
    public Result list(RoleModel roleModel,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        PageInfo<RoleModel> pageInfo = roleService.selectByFilterAndPage(roleModel, pageNum, pageSize);

        return ResponseUtil.success(pageInfo);
    }

    /**
     * 获取公司角色信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("getCompanyRole")
    public Result getCompanyRole(@RequestParam("id") int id) {
        RoleModel roleModel = roleService.selectById(id);

        return ResponseUtil.success(roleModel);
    }

    /**
     * 添加公司部门信息
     *
     * @param roleModel
     * @return
     */
    @ResponseBody
    @RequestMapping("addCompanyRole")
    public Result addCompanyRole(RoleModel roleModel) {
        int addCnt = roleService.save(roleModel);

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
    @RequestMapping("delCompanyRole")
    public Result disableCompanyRole(@RequestParam("id") int id, @RequestParam("status") int status) {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(id);
        roleModel.setStatus(status);
        int updateCnt = roleService.updateNotNull(roleModel);

        return ResponseUtil.success();
    }
}
