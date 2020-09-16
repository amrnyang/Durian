package com.swing.sky.system.api.tiku;

import cn.hutool.core.convert.Convert;
import com.swing.sky.system.module.annotation.OperateLog;
import com.swing.sky.system.module.constants.BusinessTypeConstants;
import com.swing.sky.system.module.constants.ModuleConstants;
import com.swing.sky.common.utils.wx.RichTextUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiAnswerService;
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
 * 题库模块-答案与解析
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("tiku/answer")
public class TiAnswerController extends BasicController {
    private TiAnswerService answerService;
    private TiQuestionService questionService;

    @Autowired
    public void setAnswerService(TiAnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:list')")
    public TableDataInfo list(TiAnswerDO answer) {
        startPage();
        List<TiAnswerDO> list = answerService.listByCondition(answer, null, null);
        /*精简数据，便于传输*/
        list.forEach(a -> a.setAnswer(""));
        list.forEach(a -> a.setAnalysis(""));
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add/{questionId}")
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:add')")
    public String add(@PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionService.getById(questionId));
        return "tiku/answer/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:add')")
    @OperateLog(module = ModuleConstants.TIKU_ANSWER, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated TiAnswerDO answer) {
        //新增答案默认进入审核中
        answer.setAuditStatus("A");
        //对图片的格式进行筛选，宽度不大于350px，适应手机大小
        answer.setAnswer(RichTextUtils.resizePicture(answer.getAnswer()));
        answer.setAnalysis(RichTextUtils.resizePicture(answer.getAnalysis()));
        answerService.insert(answer);
        return SkyResponse.success("新增成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{answerId}")
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:edit')")
    public String edit(@PathVariable("answerId") Long answerId, Model model) {
        TiAnswerDO answer = answerService.getById(answerId);
        model.addAttribute("answer", answer);
        model.addAttribute("question", questionService.getById(answer.getQuestionId()));
        return "tiku/answer/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:edit')")
    @OperateLog(module = ModuleConstants.TIKU_ANSWER, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated TiAnswerDO answer) {
        answer.setAuditStatus("A");
        //对图片的格式进行筛选，宽度不大于350px，适应手机大小
        answer.setAnswer(RichTextUtils.resizePicture(answer.getAnswer()));
        answer.setAnalysis(RichTextUtils.resizePicture(answer.getAnalysis()));
        answerService.update(answer);
        return SkyResponse.success("更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:remove')")
    @OperateLog(module = ModuleConstants.TIKU_ANSWER, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] answerIds = Convert.toLongArray(ids);
        answerService.batchDeleteByIds(answerIds);
        return SkyResponse.success("删除成功！");
    }

    /**
     * 复审
     */
    @PostMapping("reAudit/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:reAudit')")
    public SkyResponse reAudit(@PathVariable("id") Long id) {
        TiAnswerDO answer = new TiAnswerDO();
        answer.setId(id);
        answer.setAuditStatus("A");
        answerService.update(answer);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 显示答案详情
     */
    @GetMapping("/detail/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        TiAnswerDO answer = answerService.getById(id);
        model.addAttribute("answer", answer);
        return "tiku/answer/answerDetail";
    }
}
