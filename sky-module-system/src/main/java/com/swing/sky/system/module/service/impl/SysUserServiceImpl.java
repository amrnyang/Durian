package com.swing.sky.system.module.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.system.module.service.SysUserService;
import com.swing.sky.system.module.dao.SysUserDAO;
import com.swing.sky.system.module.dao.SysUserPostLinkDAO;
import com.swing.sky.system.module.dao.SysUserRoleLinkDAO;
import com.swing.sky.system.module.domain.SysUserDO;
import com.swing.sky.system.module.domain.SysUserPostLinkDO;
import com.swing.sky.system.module.domain.SysUserRoleLinkDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Resource
    private SysUserDAO userDAO;

    @Resource
    private SysUserRoleLinkDAO userRoleLinkDAO;

    @Resource
    private SysUserPostLinkDAO userPostLinkDAO;

    @Override
    @SkyServiceAuthority(moduleName = "user")
    public int insert(SysUserDO sysUserDO) {
        //校验信息是否冲突
        if (userDAO.countByUserName(sysUserDO.getUsername(), -1L) > 0) {
            throw new RuntimeException("新增用户'" + sysUserDO.getUsername() + "'失败，登录账号已存在");
        } else if (userDAO.countByPhone(sysUserDO.getPhone(), -1L) > 0) {
            throw new RuntimeException("新增用户'" + sysUserDO.getUsername() + "'失败，手机号码已存在");
        } else if (userDAO.countByEmail(sysUserDO.getEmail(), -1L) > 0) {
            throw new RuntimeException("新增用户'" + sysUserDO.getUsername() + "'失败，邮箱账号已存在");
        }
        //密码加密
        if (sysUserDO.getPassword() != null) {
            sysUserDO.setPassword(new BCryptPasswordEncoder().encode(sysUserDO.getPassword()));
        }
        // 新增用户信息
        return userDAO.insert(sysUserDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "user")
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteById(Long id) {
        int i = userDAO.deleteById(id);
        //删除用户关联信息
        int i1 = userRoleLinkDAO.deleteItemByOneId(id);
        int i2 = userPostLinkDAO.deleteItemByOneId(id);
        return i + i1 + i2;
    }

    @Override
    @SkyServiceAuthority(moduleName = "user")
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchDeleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
        return 1;
    }

    @Override
    @SkyServiceAuthority(moduleName = "user")
    public int update(SysUserDO sysUserDO) {
        //校验信息是否冲突
        if (userDAO.countByPhone(sysUserDO.getPhone(), sysUserDO.getId()) > 0) {
            throw new RuntimeException("更新用户'" + sysUserDO.getUsername() + "'失败，手机号码已存在");
        } else if (userDAO.countByEmail(sysUserDO.getEmail(), sysUserDO.getId()) > 0) {
            throw new RuntimeException("更新用户'" + sysUserDO.getUsername() + "'失败，邮箱账号已存在");
        }
        //密码加密
        if (sysUserDO.getPassword() != null) {
            sysUserDO.setPassword(new BCryptPasswordEncoder().encode(sysUserDO.getPassword()));
        }
        return userDAO.update(sysUserDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "user")
    public SysUserDO getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public List<SysUserDO> listByCondition(SysUserDO sysUserDO, String beginTime, String endTime) {
        return userDAO.listByCondition(sysUserDO, beginTime, endTime);
    }

    @Override
    public List<SysUserDO> listByConditionAndUserId(Long userId, SysUserDO t, String beginTime, String endTime) {
        if (AdminUtils.isAdminUser(userId)) {
            return userDAO.listByCondition(t, beginTime, endTime);
        }
        return userDAO.listByConditionAndUserId(userId, t, beginTime, endTime);
    }

    @Override
    public SysUserDO getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateUserPostLink(Long userId, Long[] postIds) {
        //删除并插入关联
        userPostLinkDAO.deleteItemByOneId(userId);
        return insertUserPost(userId, postIds);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateUserRoleLink(Long userId, Long[] roleIds) {
        //删除并插入关联
        userRoleLinkDAO.deleteItemByOneId(userId);
        return insertUserRole(userId, roleIds);
    }


    // -----------------------------------非接口方法--------------------------------------------

    /**
     * 插入用户角色关联信息
     *
     * @param userId  用户id
     * @param roleIds 角色集合
     * @return 影响行数
     */
    public int insertUserRole(Long userId, Long[] roleIds) {
        List<SysUserRoleLinkDO> userRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysUserRoleLinkDO userRoleDO = new SysUserRoleLinkDO();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(roleId);
            userRoles.add(userRoleDO);
        }
        if (userRoles.size() > 0) {
            int i = userRoleLinkDAO.batchInsert(userRoles);
            log.info("插入用户角色关联信息结果:" + i);
            return i;
        }
        return 0;
    }


    /**
     * 插入用户岗位关联信息
     *
     * @param userId  用户id
     * @param postIds 岗位集合
     * @return 影响行数
     */
    public int insertUserPost(Long userId, Long[] postIds) {
        List<SysUserPostLinkDO> userPostList = new ArrayList<>();
        for (Long postId : postIds) {
            SysUserPostLinkDO userPostLinkDO = new SysUserPostLinkDO();
            userPostLinkDO.setUserId(userId);
            userPostLinkDO.setPostId(postId);
            userPostList.add(userPostLinkDO);
        }
        if (userPostList.size() > 0) {
            int i = userPostLinkDAO.batchInsert(userPostList);
            log.info("插入用户岗位关联信息结果:" + i);
            return i;
        }
        return 0;
    }


}
