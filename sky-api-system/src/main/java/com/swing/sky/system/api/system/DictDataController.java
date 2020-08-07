package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysDictDataDO;
import com.swing.sky.system.module.service.SysDictDataService;
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
@RequestMapping("system/dict/data")
public class DictDataController extends BasicController {

    @Autowired
    private SysDictDataService dictDataService;


    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:list')")
    public TableDataInfo list(SysDictDataDO dictData) {
        startPage();
        List<SysDictDataDO> list = dictDataService.listByCondition(dictData, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add/{typeId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dict:add')")
    public String add(@PathVariable("typeId") Long typeId, Model model) {
        model.addAttribute("typeId", typeId);
        return "system/dict/data/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:add')")
    @OperateLog(module = ModuleConstants.DICT_DATA, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysDictDataDO dictData) {
        dictDataService.insert(dictData);
        return SkyResponse.success("字典数据插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{dictDataId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dict:edit')")
    public String edit(@PathVariable("dictDataId") Long dictDataId, Model model) {
        model.addAttribute("dictData", dictDataService.getById(dictDataId));
        return "system/dict/data/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:edit')")
    @OperateLog(module = ModuleConstants.DICT_DATA, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysDictDataDO dictData) {
        dictDataService.update(dictData);
        //如果将该数据设置为默认，则将该字典类型的的其他数据设置为非默认
        if (dictData.getIsDefault()) {
            SysDictDataDO dictDataDO = new SysDictDataDO();
            dictDataDO.setTypeId(dictDataService.getById(dictData.getId()).getTypeId());
            List<SysDictDataDO> list = dictDataService.listByCondition(dictDataDO, null, null);
            for (SysDictDataDO data : list) {
                if (!data.getId().equals(dictData.getId())) {
                    data.setIsDefault(false);
                    dictDataService.update(data);
                }
            }
        }
        return SkyResponse.success("字典数据更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:remove')")
    @OperateLog(module = ModuleConstants.DICT_DATA, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] dictDataIds = Convert.toLongArray(ids);
        dictDataService.batchDeleteByIds(dictDataIds);
        return SkyResponse.success("字典数据删除成功！");
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dict:export')")
    @OperateLog(module = ModuleConstants.DICT_DATA, businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysDictDataDO dictData) {
        List<SysDictDataDO> list = dictDataService.listByCondition(dictData, null, null);
        ExcelUtils<SysDictDataDO> excelUtils = new ExcelUtils<>(SysDictDataDO.class);
        return excelUtils.exportExcel(list, "字典数据");
    }
}
