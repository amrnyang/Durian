package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.module.domain.SysPostDO;
import com.swing.sky.system.module.service.SysPostService;
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
@RequestMapping("system/post")
public class PostController extends BasicController {
    @Autowired
    private SysPostService postService;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:post:view')")
    public String post() {
        return "system/post/post";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:post:list')")
    public TableDataInfo list(SysPostDO post) {
        startPage();
        List<SysPostDO> list = postService.listByConditionAndUserId(UserDetailsUtil.getUserId(), post, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('system:post:add')")
    public String add() {
        return "system/post/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:post:add')")
    @OperateLog(module = ModuleConstants.POST, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysPostDO postDO) {
        postService.insert(postDO);
        return SkyResponse.success("岗位信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{postId}")
    @PreAuthorize("@sca.needAuthoritySign('system:post:edit')")
    public String edit(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.getById(postId));
        return "system/post/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:post:edit')")
    @OperateLog(module = ModuleConstants.POST, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysPostDO postDO) {
        postService.update(postDO);
        return SkyResponse.success("岗位信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:post:remove')")
    @OperateLog(module = ModuleConstants.POST, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] postIds = Convert.toLongArray(ids);
        postService.batchDeleteByIds(postIds);
        return SkyResponse.success("岗位信息删除成功！");
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:post:export')")
    @OperateLog(module = ModuleConstants.POST, businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysPostDO post) {
        List<SysPostDO> list = postService.listByConditionAndUserId(UserDetailsUtil.getUserId(), post, null, null);
        ExcelUtils<SysPostDO> excelUtils = new ExcelUtils<>(SysPostDO.class);
        return excelUtils.exportExcel(list, "岗位数据");
    }
}
