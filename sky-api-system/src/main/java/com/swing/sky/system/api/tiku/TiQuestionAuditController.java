package com.swing.sky.system.api.tiku;

import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.utils.html.HtmlUtils;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.tiku.module.dao.TiDeptCourseLinkDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiCourseService;
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
 * 题目审核模块
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("tiku/question/audit")
public class TiQuestionAuditController extends BasicController {
    @Resource
    private TiDeptCourseLinkDAO deptCourseLinkDAO;
    private TiQuestionService questionService;
    private TiCourseService courseService;

    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setCourseService(TiCourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:audit:view')")
    public String config() {
        return "tiku/question/audit/audit";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:audit:list')")
    public TableDataInfo list(TiQuestionDO question) {
        startPage();
        //获取该用户所属的专业id
        Long majorId = UserDetailsUtil.getUserDO().getDeptId();
        //获取该专业所有的课程id
        Long[] courseIds = deptCourseLinkDAO.listTwoIdsByOneId(majorId);
        //获取所有课程的题目
        List<TiQuestionDO> list = questionService.listQuestionByCourseIds(courseIds);
        //指向该用户展示审核中的题目
        list = list.stream().filter(a -> ("A".equals(a.getAuditStatus()))).collect(Collectors.toList());
        /*精简数据，便于传输*/
        list.forEach(a -> a.setFullContent(""));
        list.forEach(a -> a.setContent((a.getContent() + StringUtils.getEmptyStr(22)).substring(0, 20)));
        return buildDataTable(list);
    }


    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{questionId}")
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:audit:edit')")
    public String edit(@PathVariable("questionId") Long questionId, Model model) {
        TiQuestionDO question = questionService.getById(questionId);
        model.addAttribute("question", question);
        //获取课程名
        if (question.getCourseId() != null) {
            model.addAttribute("courseName", courseService.getById(question.getCourseId()).getCourseName());
        } else {
            model.addAttribute("courseName", null);
        }
        return "tiku/question/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:audit:edit')")
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
     * 审核通过
     */
    @PostMapping("pass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:audit:pass')")
    public SkyResponse pass(@PathVariable("id") Long id) {
        TiQuestionDO question = new TiQuestionDO();
        question.setId(id);
        question.setAuditStatus("B");
        questionService.update(question);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 审核未通过
     */
    @PostMapping("unPass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:audit:unPass')")
    public SkyResponse unPass(@PathVariable("id") Long id) {
        TiQuestionDO question = new TiQuestionDO();
        question.setId(id);
        question.setAuditStatus("C");
        questionService.update(question);
        return SkyResponse.success("操作成功！！");
    }

}
