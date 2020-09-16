package com.swing.sky.system.api.tiku;

import com.swing.sky.center.module.service.CenterUserService;
import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.utils.html.HtmlUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.domain.TiQuestionSubmitDO;
import com.swing.sky.tiku.module.service.TiQuestionService;
import com.swing.sky.tiku.module.service.TiQuestionSubmitService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 题目bug审核提交
 *
 * @author swing
 * @since 2020-9-2
 */
@Api
@Controller
@RequestMapping("tiku/question/bug")
public class TiQuestionErrorSubmitController extends BasicController {
    private TiQuestionSubmitService questionSubmitService;

    private TiQuestionService questionService;

    private CenterUserService userService;

    @Autowired
    public void setUserService(CenterUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setQuestionSubmitService(TiQuestionSubmitService questionSubmitService) {
        this.questionSubmitService = questionSubmitService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:bug:view')")
    public String config() {
        return "tiku/question/bug/bug";
    }

    /**
     * 获取需要审核的题目列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:bug:list')")
    public TableDataInfo list(TiQuestionSubmitDO question) {
        startPage();
        question.setSubmitStatus("A");
        List<TiQuestionSubmitDO> list = questionSubmitService.listByCondition(question, null, null);
        if (list != null) {
            /*精简数据，便于传输*/
            list.forEach(a -> a.setFullContent(""));
            list.forEach(a -> a.setContent((a.getContent() + StringUtils.getEmptyStr(22)).substring(0, 20)));
            return buildDataTable(list);
        }
        return buildDataTable(new ArrayList<>());
    }

    /**
     * 审核通过
     */
    @PostMapping("pass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:bug:pass')")
    public SkyResponse pass(@PathVariable("id") Long id) {
        TiQuestionSubmitDO subQuestion = questionSubmitService.getById(id);
        subQuestion.setSubmitStatus("B");
        questionSubmitService.update(subQuestion);
        //将内容合并到原题目中
        TiQuestionDO questionDO = new TiQuestionDO();
        questionDO.setId(subQuestion.getQuestionId());
        questionDO.setFullContent(subQuestion.getFullContent());
        questionDO.setContent(HtmlUtils.tagsFilter(questionDO.getFullContent()));
        questionDO.setUpdateTime(new Date());
        questionDO.setUpdateBy(userService.getById(subQuestion.getCreatorId()).getUsername());
        questionService.update(questionDO);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 审核未通过
     */
    @PostMapping("unPass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:question:bug:unPass')")
    public SkyResponse unPass(@PathVariable("id") Long id) {
        TiQuestionSubmitDO question = new TiQuestionSubmitDO();
        question.setId(id);
        question.setSubmitStatus("C");
        questionSubmitService.update(question);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 题目错误提交详情
     */
    @GetMapping("/detail/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        TiQuestionSubmitDO questionSubmitDO = questionSubmitService.getById(id);
        //获取原题内容
        String fullContent = questionService.getById(questionSubmitDO.getQuestionId()).getFullContent();
        model.addAttribute("questionSub", questionSubmitDO);
        model.addAttribute("fullContent", fullContent);
        return "tiku/question/bug/bugDetail";
    }
}
