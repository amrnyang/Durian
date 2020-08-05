package com.swing.sky.framework.security.service;

import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.framework.security.utils.UserDetailsUtil;
import com.swing.sky.module.system.domain.SysMenuDO;
import com.swing.sky.module.system.service.SkipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Controller层的权限管理
 *
 * @author swing
 */
@Service("sca")
public class SkyControllerAuthority {
    private SkipService skipService;

    @Autowired
    public void setSkipService(SkipService skipService) {
        this.skipService = skipService;
    }

    /**
     * 验证该用户是否具有该接口的请求权限
     *
     * @param authoritySign 权限标识
     * @return 是否
     */
    public boolean needAuthoritySign(String authoritySign) {
        //获取该用户的id
        Long userId = UserDetailsUtil.getUserId();
        if (userId == null) {
            throw new RuntimeException("发生错误，无法确定来访者的身份！");
        }
        //如果是管理员用户,直接通行
        if (AdminUtils.isAdminUser(userId)) {
            return true;
        }
        //获取该用户的所有菜单列表
        List<SysMenuDO> menuDOList = skipService.getMenuListByUserId(userId);
        for (SysMenuDO menuDO : menuDOList) {
            if (menuDO.getAuthoritySign().equalsIgnoreCase(authoritySign)) {
                return true;
            }
        }
        return false;
    }
}
