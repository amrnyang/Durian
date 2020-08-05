package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.api.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysDictTypeDO;
import com.swing.sky.system.module.service.SysDictDataService;
import com.swing.sky.system.module.service.SysDictTypeService;
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
@RequestMapping("system/dict/type")
public class DictTypeController extends BasicController {
    @Autowired
    private SysDictTypeService dictTypeService;

    @Autowired
    private SysDictDataService dictDataService;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:dict:view')")
    public String post() {
        return "system/dict/type/type";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:list')")
    public TableDataInfo list(SysDictTypeDO dictType) {
        startPage();
        List<SysDictTypeDO> list = dictTypeService.listByCondition(dictType, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('system:dict:add')")
    public String add() {
        return "system/dict/type/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:add')")
    @OperateLog(module = ModuleConstants.DICT_TYPE,businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysDictTypeDO dictType) {
        dictTypeService.insert(dictType);
        return SkyResponse.success("字典类型插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{dictTypeId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dict:edit')")
    public String edit(@PathVariable("dictTypeId") Long dictTypeId, Model model) {
        model.addAttribute("dictType", dictTypeService.getById(dictTypeId));
        return "system/dict/type/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:edit')")
    @OperateLog(module = ModuleConstants.DICT_TYPE,businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysDictTypeDO dictType) {
        dictTypeService.update(dictType);
        return SkyResponse.success("字典类型更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:remove')")
    @OperateLog(module = ModuleConstants.DICT_TYPE,businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] dictTypeIds = Convert.toLongArray(ids);
        dictTypeService.batchDeleteByIds(dictTypeIds);
        return SkyResponse.success("字典类型删除成功！");
    }


    /**
     * 获取该字典类型的字典数据（视图）
     */
    @GetMapping("/getDictData/{typeId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dict:view')")
    public String getDictData(@PathVariable("typeId") Long typeId, Model model) {
        model.addAttribute("typeId", typeId);
        return "system/dict/data/data";
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:export')")
    @OperateLog(module = ModuleConstants.DICT_TYPE,businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysDictTypeDO dictType) {
        List<SysDictTypeDO> list = dictTypeService.listByCondition(dictType, null, null);
        ExcelUtils<SysDictTypeDO> excelUtils = new ExcelUtils<>(SysDictTypeDO.class);
        return excelUtils.exportExcel(list, "字典类型数据");
    }

}
