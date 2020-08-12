package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.module.domain.SysLogOperateDO;
import com.swing.sky.system.module.service.SysLogOperateService;
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
@RequestMapping("system/log/operate")
public class OperateLogController extends BasicController {
    @Autowired
    private SysLogOperateService logOperateService;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:log:operate:view')")
    public String logOperate() {
        return "system/log/operate/operateLog";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:log:operate:list')")
    public TableDataInfo list(SysLogOperateDO logOperate) {
        startPage();
        List<SysLogOperateDO> list = logOperateService.listByCondition(logOperate, null, null);
        return buildDataTable(list);
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:log:operate:remove')")
    @OperateLog(module = ModuleConstants.OPERATE_LOG,businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] idList = Convert.toLongArray(ids);
        logOperateService.batchDeleteByIds(idList);
        return SkyResponse.success("操作日志删除成功！");
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:log:operate:export')")
    @OperateLog(module = ModuleConstants.OPERATE_LOG,businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysLogOperateDO logOperate) {
        List<SysLogOperateDO> list = logOperateService.listByCondition(logOperate, null, null);
        ExcelUtils<SysLogOperateDO> excelUtils = new ExcelUtils<>(SysLogOperateDO.class);
        return excelUtils.exportExcel(list, "操作日志数据");
    }
}
