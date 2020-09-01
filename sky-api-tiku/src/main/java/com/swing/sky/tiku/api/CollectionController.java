package com.swing.sky.tiku.api;

import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.tiku.BasicController;
import com.swing.sky.tiku.framework.oss.OssServer;
import com.swing.sky.tiku.module.dao.TiUserQuestionLinkDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 与收藏相关的接口
 *
 * @author swing
 * @since 202-8-31
 */
@RestController
@RequestMapping("tiku/collection")
public class CollectionController extends BasicController {
    @Resource
    private OssServer ossServer;

    @Resource
    private TiUserQuestionLinkDAO userQuestionLinkDAO;

    /**
     * 点击爱心，收藏题目
     * @param id 题目id
     */
    @PostMapping("/{id}")
    public SkyResponse collectionQuestion(@PathVariable Long id, HttpServletRequest request){
        DurianUserDO loginUser = ossServer.getLoginInfo(request);
        Long userId = loginUser.getId();
        userQuestionLinkDAO.insert(userId,id);
        return SkyResponse.success("收藏成功！");
    }

    /**
     * 获取用户收藏的题目列表（为树形结构）
     * (如果题目的审核状态变为审核中或未通过或者不使用（搜索引擎中不会有这些题目的记录），但收藏区仍然可以查看这些题目，保证收藏区的稳定性）
     */
    @GetMapping("/list")
    public SkyResponse listUserCollections(HttpServletRequest request){
        startPage();
        DurianUserDO loginUser = ossServer.getLoginInfo(request);
        Long userId = loginUser.getId();
        List<TiQuestionDO> list = userQuestionLinkDAO.listTwoByOneId(userId);
        return SkyResponse.success("请求成功",1)
                .put("list",list);
    }

}
