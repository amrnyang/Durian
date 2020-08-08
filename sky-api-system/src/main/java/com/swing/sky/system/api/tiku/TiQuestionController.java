package com.swing.sky.system.api.tiku;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.common.utils.html.HtmlUtils;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiQuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库问题
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("tiku/question")
public class TiQuestionController extends BasicController {
    private TiQuestionService questionService;

    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:view')")
    public String config() {
        return "tiku/question/question";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:list')")
    public TableDataInfo list(TiQuestionDO question) {
        startPage();
        List<TiQuestionDO> list = questionService.listByCondition(question, null, null);
        /*精简数据，便于传输*/
        list.forEach(a -> a.setFullContent(""));
        list.forEach(a -> a.setContent(a.getContent().substring(0, 20)));
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:add')")
    public String add() {
        return "tiku/question/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:add')")
    @OperateLog(module = ModuleConstants.TIKU_QUESTION, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated TiQuestionDO question) {
        //新增题目默认进入审核中
        question.setAuditStatus("A");
        //从完整内容中提取摘要，供搜索引擎搜索
        question.setContent(HtmlUtils.tagsFilter(question.getFullContent()));
        questionService.insert(question);
        return SkyResponse.success("配置信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{questionId}")
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:edit')")
    public String edit(@PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionService.getById(questionId));
        return "tiku/question/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:edit')")
    @OperateLog(module = ModuleConstants.TIKU_QUESTION, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated TiQuestionDO question) {
        //更新题目默认进入审核中
        question.setAuditStatus("A");
        //从完整内容中提取摘要，供搜索引擎搜索
        question.setContent(HtmlUtils.tagsFilter(question.getFullContent()));
        questionService.update(question);
        return SkyResponse.success("题目信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:remove')")
    @OperateLog(module = ModuleConstants.TIKU_QUESTION, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] questionIds = Convert.toLongArray(ids);
        questionService.batchDeleteByIds(questionIds);
        return SkyResponse.success("配置信息删除成功！");
    }

    /**
     * 显示题目详情
     */
    @GetMapping("/detail/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        TiQuestionDO questionDO = questionService.getById(id);
        model.addAttribute("question", questionDO);
        return "tiku/question/questionDetail";
    }
}
