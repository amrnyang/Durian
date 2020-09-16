package com.swing.sky.tiku.api;

import com.swing.sky.center.inner.UserRequestService;
import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.common.utils.html.HtmlUtils;
import com.swing.sky.common.utils.wx.RichTextUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.tiku.BasicController;
import com.swing.sky.tiku.module.domain.TiAnswerSubmitDO;
import com.swing.sky.tiku.module.domain.TiQuestionSubmitDO;
import com.swing.sky.tiku.module.service.TiAnswerSubmitService;
import com.swing.sky.tiku.module.service.TiQuestionSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 错误提交相关的接口
 *
 * @author swing
 * @since 2020-9-1
 */
@RestController
@RequestMapping("tiku/submit/error")
public class ErrorSubmitController extends BasicController {
    private TiQuestionSubmitService questionSubmitService;
    private TiAnswerSubmitService answerSubmitService;

    @Resource
    private UserRequestService userRequestService;


    @Autowired
    public void setAnswerSubmitService(TiAnswerSubmitService answerSubmitService) {
        this.answerSubmitService = answerSubmitService;
    }

    @Autowired
    public void setQuestionSubmitService(TiQuestionSubmitService questionSubmitService) {
        this.questionSubmitService = questionSubmitService;
    }

    /**
     * 提交答案bug
     *
     * @param answerSubmit 提交信息
     * @param request      请求
     * @return 响应
     */
    @PostMapping("/answer")
    public SkyResponse submitErrorAnswer(@Validated TiAnswerSubmitDO answerSubmit, HttpServletRequest request) {
        CenterUserDO loginUser = userRequestService.getLoginInfo(request);
        answerSubmit.setCreatorId(loginUser.getId());
        //提交的bug默认进入审核中
        answerSubmit.setSubmitStatus("A");
        answerSubmit.setSubmitTime(new Date());
        answerSubmitService.insert(answerSubmit);
        return SkyResponse.success("答案bug提交成功！");
    }

    /**
     * 获取我提交的答案bug列表
     */
    @GetMapping("/answer/list")
    public SkyResponse getAnswerSubmitList(HttpServletRequest request) {
        startPage();
        CenterUserDO loginUser = userRequestService.getLoginInfo(request);
        Long userId = loginUser.getId();
        TiAnswerSubmitDO answerSubmit = new TiAnswerSubmitDO();
        answerSubmit.setCreatorId(userId);
        List<TiAnswerSubmitDO> list = answerSubmitService.listByCondition(answerSubmit, null, null);
        return SkyResponse.success("请求成功", 1)
                .put("list", list);
    }

    /**
     * 提交题目bug
     *
     * @param questionSubmit 提交信息
     * @param request        请求
     * @return 响应
     */
    @PostMapping("/question")
    public SkyResponse submitErrorAnswer(@Validated TiQuestionSubmitDO questionSubmit, HttpServletRequest request) {
        CenterUserDO loginUser = userRequestService.getLoginInfo(request);
        questionSubmit.setCreatorId(loginUser.getId());
        //提交的bug默认进入审核中
        questionSubmit.setSubmitStatus("A");
        questionSubmit.setSubmitTime(new Date());
        //从完整内容中提取摘要，供搜索引擎搜索
        questionSubmit.setContent(HtmlUtils.tagsFilter(questionSubmit.getFullContent()));
        //对图片的格式进行筛选，宽度不大于350px，适应手机大小
        questionSubmit.setFullContent(RichTextUtils.resizePicture(questionSubmit.getFullContent()));
        questionSubmitService.insert(questionSubmit);
        return SkyResponse.success("题目bug提交成功！");
    }

    /**
     * 获取我提交的题目bug列表
     */
    @GetMapping("/question/list")
    public SkyResponse getQuestionSubmitList(HttpServletRequest request) {
        startPage();
        CenterUserDO loginUser = userRequestService.getLoginInfo(request);
        Long userId = loginUser.getId();
        TiQuestionSubmitDO questionSubmit = new TiQuestionSubmitDO();
        questionSubmit.setCreatorId(userId);
        List<TiQuestionSubmitDO> list = questionSubmitService.listByCondition(questionSubmit, null, null);
        return SkyResponse.success("请求成功", 1)
                .put("list", list);
    }
}
