package com.swing.sky.system.api.tiku;

import com.swing.sky.center.module.service.CenterUserService;
import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.domain.TiAnswerSubmitDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiAnswerService;
import com.swing.sky.tiku.module.service.TiAnswerSubmitService;
import com.swing.sky.tiku.module.service.TiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 提交错误答案审核
 *
 * @author swing
 * @since 2020-9-15
 */
@Controller
@RequestMapping("tiku/answer/bug")
public class TiAnswerErrorSubmitController extends BasicController {
    private TiAnswerSubmitService answerSubmitService;

    private TiAnswerService answerService;

    private TiQuestionService questionService;

    private CenterUserService userService;


    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setUserService(CenterUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAnswerService(TiAnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setAnswerSubmitService(TiAnswerSubmitService answerSubmitService) {
        this.answerSubmitService = answerSubmitService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:bug:view')")
    public String config() {
        return "tiku/answer/bug/bug";
    }

    /**
     * 获取需要审核的题目列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:bug:list')")
    public TableDataInfo list(TiAnswerSubmitDO question) {
        startPage();
        question.setSubmitStatus("A");
        List<TiAnswerSubmitDO> list = answerSubmitService.listByCondition(question, null, null);
        if (list != null) {
            /*精简数据，便于传输*/
            list.forEach(a -> a.setAnswer((a.getAnswer() + StringUtils.getEmptyStr(22)).substring(0, 20)));
            list.forEach(a -> a.setAnalysis((a.getAnalysis() + StringUtils.getEmptyStr(22)).substring(0, 20)));
            return buildDataTable(list);
        }
        return buildDataTable(new ArrayList<>());
    }

    /**
     * 审核通过
     */
    @PostMapping("pass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:bug:pass')")
    public SkyResponse pass(@PathVariable("id") Long id) {
        TiAnswerSubmitDO subAnswer = answerSubmitService.getById(id);
        subAnswer.setSubmitStatus("B");
        answerSubmitService.update(subAnswer);
        //将内容合并到原题目中
        TiAnswerDO answerDO = new TiAnswerDO();
        answerDO.setId(subAnswer.getAnswerId());
        answerDO.setAnswer(subAnswer.getAnswer());
        answerDO.setAnalysis(subAnswer.getAnalysis());
        answerDO.setUpdateTime(new Date());
        answerDO.setUpdateBy(userService.getById(subAnswer.getCreatorId()).getUsername());
        answerService.update(answerDO);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 审核未通过
     */
    @PostMapping("unPass/{id}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:answer:bug:unPass')")
    public SkyResponse unPass(@PathVariable("id") Long id) {
        TiAnswerSubmitDO answer = new TiAnswerSubmitDO();
        answer.setId(id);
        answer.setSubmitStatus("C");
        answerSubmitService.update(answer);
        return SkyResponse.success("操作成功！！");
    }

    /**
     * 题目错误提交详情
     */
    @GetMapping("/detail/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        TiAnswerSubmitDO answerSubmitDO = answerSubmitService.getById(id);
        //获取题目内容
        TiQuestionDO question = questionService.getById(answerService.getById(answerSubmitDO.getAnswerId()).getQuestionId());
        //获取原题内容
        TiAnswerDO oldAnswer = answerService.getById(answerSubmitDO.getAnswerId());
        model.addAttribute("question", question);
        model.addAttribute("oldAnswer", oldAnswer);
        model.addAttribute("newAnswer", answerSubmitDO);
        return "tiku/answer/bug/bugDetail";
    }
}
