package com.swing.sky.tiku.api;

import com.swing.sky.common.utils.wx.RichTextUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.tiku.BasicController;
import com.swing.sky.tiku.framework.oss.OssServer;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.service.TiAnswerService;
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
 * 与答案相关的接口
 *
 * @author swing
 * @since 202-8-31
 */
@RestController
@RequestMapping("tiku/answer")
public class AnswerController extends BasicController {
    @Resource
    private OssServer ossServer;

    private TiAnswerService answerService;

    @Autowired
    public void setAnswerService(TiAnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * 上传题目
     */
    @PostMapping("/add")
    public SkyResponse addAnswer(@Validated TiAnswerDO answer, HttpServletRequest request) {
        DurianUserDO loginUser = ossServer.getLoginInfo(request);
        answer.setCreatorId(loginUser.getId());
        answer.setCreateBy(loginUser.getUsername());
        answer.setCreateTime(new Date());
        answer.setAuditStatus("A");
        //限制富文本中的图片大小
        answer.setAnswer(RichTextUtils.resizePicture(answer.getAnswer()));
        answer.setAnalysis(RichTextUtils.resizePicture(answer.getAnalysis()));
        answerService.insert(answer);
        return SkyResponse.success("答案提交成功！");
    }

    /**
     * 查询我的上传的答案列表
     */
    @GetMapping("/add/list")
    public SkyResponse getAnswerList(HttpServletRequest request) {
        startPage();
        DurianUserDO loginUser = ossServer.getLoginInfo(request);
        Long userId = loginUser.getId();
        TiAnswerDO answer = new TiAnswerDO();
        answer.setCreatorId(userId);
        List<TiAnswerDO> list = answerService.listByCondition(answer, null, null);
        return SkyResponse.success("请求成功", 1)
                .put("list", list);
    }

}
