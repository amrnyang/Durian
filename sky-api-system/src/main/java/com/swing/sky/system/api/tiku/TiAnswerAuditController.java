package com.swing.sky.system.api.tiku;

import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.common.utils.wx.RichTextUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.tiku.module.dao.TiDeptCourseLinkDAO;
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

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 答案审核模块
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("tiku/answer/audit")
public class TiAnswerAuditController extends BasicController {
    @Resource
    private TiDeptCourseLinkDAO deptCourseLinkDAO;
    private TiQuestionService questionService;
    private TiAnswerService answerService;

    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setAnswerService(TiAnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:audit:view')")
    public String config() {
        return "tiku/answer/audit/audit";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:audit:list')")
    public TableDataInfo list(TiAnswerDO answer) {
        startPage();
        //获取该用户所属的专业id
        Long majorId = UserDetailsUtil.getUserDO().getDeptId();
        //获取该专业所有的课程id
        Long[] courseIds = deptCourseLinkDAO.listTwoIdsByOneId(majorId);
        //获取所有课程的题目
        List<TiQuestionDO> list = questionService.listQuestionByCourseIds(courseIds);
        //获取这些题目所有的答案
        List<TiAnswerDO> answerList = answerService.listAnswersByQuestions(list);
        //指向该用户展示审核中的题目
        answerList = answerList.stream().filter(a -> ("A".equals(a.getAuditStatus()))).collect(Collectors.toList());
        /*精简数据，便于传输*/
        answerList.forEach(a -> a.setAnswer(""));
        answerList.forEach(a -> a.setAnalysis(""));
        return buildDataTable(answerList);
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
     * 审核通过
     */
    @PostMapping("pass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:audit:pass')")
    public SkyResponse pass(@PathVariable("id") Long id) {
        TiAnswerDO answer = new TiAnswerDO();
        answer.setId(id);
        answer.setAuditStatus("B");
        answerService.update(answer);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 审核未通过
     */
    @PostMapping("unPass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:audit:unPass')")
    public SkyResponse unPass(@PathVariable("id") Long id) {
        TiAnswerDO answer = new TiAnswerDO();
        answer.setId(id);
        answer.setAuditStatus("C");
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
