package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysNoticeDO;
import com.swing.sky.system.module.service.SysNoticeService;
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
@RequestMapping("system/notice")
public class NoticeController extends BasicController {
    @Autowired
    private SysNoticeService noticeService;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:notice:view')")
    public String notice() {
        return "system/notice/notice";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:notice:list')")
    public TableDataInfo list(SysNoticeDO notice) {
        startPage();
        List<SysNoticeDO> list = noticeService.listByCondition(notice, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('system:notice:add')")
    public String add() {
        return "system/notice/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:notice:add')")
    @OperateLog(module = ModuleConstants.NOTICE, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysNoticeDO notice) {
        noticeService.insert(notice);
        return SkyResponse.success("公告信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{noticeId}")
    @PreAuthorize("@sca.needAuthoritySign('system:notice:edit')")
    public String edit(@PathVariable("noticeId") Long noticeId, Model model) {
        model.addAttribute("notice", noticeService.getById(noticeId));
        return "system/notice/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:notice:edit')")
    @OperateLog(module = ModuleConstants.NOTICE, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysNoticeDO notice) {
        noticeService.update(notice);
        return SkyResponse.success("公告信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:notice:remove')")
    @OperateLog(module = ModuleConstants.NOTICE, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] noticeIds = Convert.toLongArray(ids);
        noticeService.batchDeleteByIds(noticeIds);
        return SkyResponse.success("公告信息删除成功！");
    }
}
