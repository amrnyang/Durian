package com.swing.sky.system.module.service.impl;

import com.swing.sky.system.module.dao.*;
import com.swing.sky.system.module.domain.*;
import com.swing.sky.system.module.service.SkipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SkipServiceImpl implements SkipService {
    @Resource
    private SysUserRoleLinkDAO userRoleLinkDAO;

    @Resource
    private SysRoleDeptLinkDAO roleDeptLinkDAO;

    @Resource
    private SysRoleMenuLinkDAO roleMenuLinkDAO;

    @Resource
    private SysPostDeptLinkDAO postDeptLinkDAO;

    @Resource
    private SysUserDAO userDAO;

    @Override
    public List<SysUserDO> getUserListByUserId(Long userId) {
        return userDAO.listByDeptIds(roleDeptLinkDAO.listTwoIdsByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId)));
    }

    @Override
    public Long[] getUserIdsByUserId(Long userId) {
        return userDAO.listIdsByDeptIds(roleDeptLinkDAO.listTwoIdsByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId)));
    }

    @Override
    public List<SysRoleDO> getRoleListByUserId(Long userId) {
        return userRoleLinkDAO.listTwoByOneId(userId);
    }

    @Override
    public Long[] getRoleIdsByUserId(Long userId) {
        return userRoleLinkDAO.listTwoIdsByOneId(userId);
    }

    @Override
    public List<SysDeptDO> getDeptListByUserId(Long userId) {
        return roleDeptLinkDAO.listTwoByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId));
    }

    @Override
    public Long[] getDeptIdsByUserId(Long userId) {
        return roleDeptLinkDAO.listTwoIdsByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId));
    }

    @Override
    public List<SysMenuDO> getMenuListByUserId(Long userId) {
        return roleMenuLinkDAO.listTwoByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId));
    }

    @Override
    public Long[] getMenuIdsByUserId(Long userId) {
        return roleMenuLinkDAO.listTwoIdsByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId));
    }

    @Override
    public List<SysPostDO> getPostListByUserId(Long userId) {
        return postDeptLinkDAO.listOneByTwoIds(roleDeptLinkDAO.listTwoIdsByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId)));
    }

    @Override
    public Long[] getPostIdsByUserId(Long userId) {
        return postDeptLinkDAO.listOneIdsByTwoIds(roleDeptLinkDAO.listTwoIdsByOneIds(userRoleLinkDAO.listTwoIdsByOneId(userId)));
    }
}
