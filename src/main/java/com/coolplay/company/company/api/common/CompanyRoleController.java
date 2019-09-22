package com.coolplay.company.company.api.common;

import com.coolplay.company.common.utils.PageConvertUtil;
import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.company.model.CompanyDeptModel;
import com.coolplay.company.core.model.RoleFunctionModel;
import com.coolplay.company.core.model.RoleModel;
import com.coolplay.company.security.security.CoolplayUserCache;
import com.coolplay.company.security.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2019/9/19.
 */
@Controller
@RequestMapping("/api/common/companyRole")
public class CompanyRoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private CoolplayUserCache coolplayUserCache;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map list(RoleModel roleModel,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "15") int pageSize) {
        PageInfo<RoleModel> pageInfo = roleService.selectByFilterAndPage(roleModel, pageNum, pageSize);

        return PageConvertUtil.grid(pageInfo);
    }

    /**
     * 获取公司角色信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCompanyRole", method = RequestMethod.GET)
    public Result getCompanyRole(@RequestParam("id") int id) {
        RoleModel roleModel = roleService.selectById(id);

        return ResponseUtil.success(roleModel);
    }

    /**
     * 添加公司角色信息
     *
     * @param roleModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCompanyRole", method = RequestMethod.POST)
    public Result addCompanyRole(RoleModel roleModel) {
        int addCnt = roleService.save(roleModel);

        return ResponseUtil.success();
    }

    /**
     * 禁用或启用公司角色
     *
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delCompanyRole", method = RequestMethod.GET)
    public Result disableCompanyRole(@RequestParam("id") int id, @RequestParam("status") int status) {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(id);
        roleModel.setStatus(status);
        int updateCnt = roleService.updateNotNull(roleModel);
        List<Integer> userIds = roleService.getUserIdsByRoleId(id);

        //清除角色缓存信息
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (Integer userId : userIds) {
                coolplayUserCache.removeUserFromCacheByUserId(userId);
            }
        }

        return ResponseUtil.success();
    }

    /**
     * 获取角色菜单信息
     *
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCompanyRoleFunctions", method = RequestMethod.GET)
    public Result getRoleFunctions(@RequestParam("roleId") int roleId) {
        List<RoleFunctionModel> roleFunctionModels = roleService.getRoleFunctionByRoleId(roleId);

        return ResponseUtil.success(roleFunctionModels);
    }
}