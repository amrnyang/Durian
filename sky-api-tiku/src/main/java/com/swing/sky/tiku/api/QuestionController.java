package com.swing.sky.tiku.api;

import com.swing.sky.common.utils.html.HtmlUtils;
import com.swing.sky.common.utils.wx.RichTextUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.tiku.BasicController;
import com.swing.sky.tiku.framework.oss.OssServer;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiAnswerService;
import com.swing.sky.tiku.module.service.TiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
 * 与题目相关的接口
 *
 * @author swing
 * @since 2020-9-1
 */
@RestController
@RequestMapping("tiku/question")
public class QuestionController extends BasicController {
    @Resource
    private OssServer ossServer;

    /**
     * 接口访问密钥
     */
    @Value("${sky-security.accessKey}")
    private String accessKey;

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
     * 根据question_id获取问题的详细信息和答案列表
     */
    @GetMapping
    public SkyResponse getQuestionDetail(Long id, String key) {
        if (key == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的密钥");
        }
        if (id == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "请输入有效的题号");
        }
        if (!key.equalsIgnoreCase(accessKey)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "无效的访问密钥，请重新输入");
        }
        TiQuestionDO question = questionService.getById(id);
        if (question == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的题目");
        }
        //获取改题目的答案列表(已审核的答案）
        TiAnswerDO answer = new TiAnswerDO();
        answer.setAuditStatus("B");
        answer.setQuestionId(id);
        List<TiAnswerDO> answerList = answerService.listByCondition(answer, null, null);
        return SkyResponse.success(2)
                .put("question", question)
                .put("answerList", answerList);
    }

    /**
     * 提交题目
     */
    @PostMapping("/add")
    public SkyResponse addQuestion(@Validated TiQuestionDO question, HttpServletRequest request) {
        //使用请求携带的token去登陆中心请求用户的信息
        DurianUserDO loginUser = ossServer.getLoginInfo(request);
        question.setCreatorId(loginUser.getId());
        question.setCreateBy(loginUser.getUsername());
        question.setCreateTime(new Date());
        //新增题目默认进入审核中
        question.setAuditStatus("A");
        //从完整内容中提取摘要，供搜索引擎搜索
        question.setContent(HtmlUtils.tagsFilter(question.getFullContent()));
        //对图片的格式进行筛选，宽度不大于350px，适应手机大小
        question.setFullContent(RichTextUtils.resizePicture(question.getFullContent()));
        questionService.insert(question);
        return SkyResponse.success("题目提交成功！");
    }

    /**
     * 查询我的上传的答案列表
     */
    @GetMapping("/add/list")
    public SkyResponse getQuestionList(HttpServletRequest request) {
        startPage();
        DurianUserDO loginUser = ossServer.getLoginInfo(request);
        Long userId = loginUser.getId();
        TiQuestionDO question = new TiQuestionDO();
        question.setCreatorId(userId);
        List<TiQuestionDO> list = questionService.listByCondition(question, null, null);
        return SkyResponse.success("请求成功", 1)
                .put("list", list);
    }
}
