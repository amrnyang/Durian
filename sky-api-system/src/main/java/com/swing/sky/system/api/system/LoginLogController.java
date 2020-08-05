package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.api.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysLogLoginDO;
import com.swing.sky.system.module.service.SysLogLoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author swing
 */
@Api
@Controller
@RequestMapping("system/log/login")
public class LoginLogController extends BasicController {
    @Autowired
    private SysLogLoginService logLoginService;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:log:login:view')")
    public String logLogin() {
        return "system/log/login/loginLog";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:log:login:list')")
    public TableDataInfo list(SysLogLoginDO logLoginDO) {
        startPage();
        List<SysLogLoginDO> list = logLoginService.listByCondition(logLoginDO, null, null);
        return buildDataTable(list);
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:log:login:remove')")
    @OperateLog(module = ModuleConstants.LOGIN_LOG, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] idList = Convert.toLongArray(ids);
        logLoginService.batchDeleteByIds(idList);
        return SkyResponse.success("登录日志删除成功！");
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:log:login:export')")
    @OperateLog(module = ModuleConstants.LOGIN_LOG, businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysLogLoginDO logLogin) {
        List<SysLogLoginDO> list = logLoginService.listByCondition(logLogin, null, null);
        ExcelUtils<SysLogLoginDO> excelUtils = new ExcelUtils<>(SysLogLoginDO.class);
        return excelUtils.exportExcel(list, "登录日志数据");
    }
}
