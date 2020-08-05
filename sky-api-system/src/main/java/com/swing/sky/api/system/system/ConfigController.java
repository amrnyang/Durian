package com.swing.sky.api.system.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.framework.excel.util.ExcelUtils;
import com.swing.sky.framework.web.SkyResponse;
import com.swing.sky.framework.web.dto.response.table.TableDataInfo;
import com.swing.sky.api.system.BasicController;
import com.swing.sky.module.system.domain.SysConfigDO;
import com.swing.sky.module.system.service.SysConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author swing
 */
@Api
@Controller
@RequestMapping("system/config")
public class ConfigController extends BasicController {
    @Autowired
    private SysConfigService configService;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:config:view')")
    public String config() {
        return "system/config/config";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:config:list')")
    public TableDataInfo list(SysConfigDO config) {
        startPage();
        List<SysConfigDO> list = configService.listByCondition(config, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('system:config:add')")
    public String add() {
        return "system/config/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:config:add')")
    @OperateLog(module = ModuleConstants.CONFIG, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysConfigDO config) {
        configService.insert(config);
        return SkyResponse.success("配置信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{configId}")
    @PreAuthorize("@sca.needAuthoritySign('system:config:edit')")
    public String edit(@PathVariable("configId") Long configId, Model model) {
        model.addAttribute("config", configService.getById(configId));
        return "system/config/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:config:edit')")
    @OperateLog(module = ModuleConstants.CONFIG, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysConfigDO config) {
        configService.update(config);
        return SkyResponse.success("配置信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:config:remove')")
    @OperateLog(module = ModuleConstants.CONFIG, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] configIds = Convert.toLongArray(ids);
        configService.batchDeleteByIds(configIds);
        return SkyResponse.success("配置信息删除成功！");
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:config:export')")
    @OperateLog(module = ModuleConstants.CONFIG, businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysConfigDO config) {
        List<SysConfigDO> list = configService.listByCondition(config, null, null);
        ExcelUtils<SysConfigDO> excelUtils = new ExcelUtils<>(SysConfigDO.class);
        return excelUtils.exportExcel(list, "参数数据");
    }
}
