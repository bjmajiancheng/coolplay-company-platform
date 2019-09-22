package com.coolplay.company.company.api.company;

import com.coolplay.company.common.utils.PageConvertUtil;
import com.coolplay.company.common.utils.ResponseUtil;
import com.coolplay.company.common.utils.Result;
import com.coolplay.company.company.model.CompanyCircleModel;
import com.coolplay.company.company.model.CoolplayBaseModel;
import com.coolplay.company.company.service.ICoolplayBaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by majiancheng on 2019/9/22.
 */
@Controller
@RequestMapping("/api/company/coolplayBase")
public class CoolplayBaseController {

    @Autowired
    private ICoolplayBaseService coolplayBaseService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map list(CoolplayBaseModel coolplayBaseModel,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "15") int pageSize) {

        PageInfo<CoolplayBaseModel> pageInfo = coolplayBaseService.selectByFilterAndPage(coolplayBaseModel, pageNum, pageSize);


        return PageConvertUtil.grid(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value="/getCoolplayBase", method = RequestMethod.GET)
    public Result getCoolplayBase(@RequestParam("id") int id) {
        CoolplayBaseModel coolplayBaseModel = coolplayBaseService.findById(id);

        return ResponseUtil.success(coolplayBaseModel);
    }

    @ResponseBody
    @RequestMapping(value="/delCoolplayBase", method = RequestMethod.GET)
    public Result delCoolplayBase(@RequestParam("id") int id, @RequestParam("isDel") int isDel) {
        CoolplayBaseModel coolplayBaseModel = new CoolplayBaseModel();
        coolplayBaseModel.setId(id);
        coolplayBaseModel.setIsDel(isDel);

        int updateCnt = coolplayBaseService.updateNotNull(coolplayBaseModel);

        return ResponseUtil.success();
    }

    @ResponseBody
    @RequestMapping(value="/closeCoolplayBase", method = RequestMethod.GET)
    public Result closeCoolplayBase(@RequestParam("id") int id, @RequestParam("isClose") int isClose) {
        CoolplayBaseModel coolplayBaseModel = new CoolplayBaseModel();
        coolplayBaseModel.setId(id);
        coolplayBaseModel.setIsClose(isClose);

        int updateCnt = coolplayBaseService.updateNotNull(coolplayBaseModel);
        return ResponseUtil.success();
    }
}
