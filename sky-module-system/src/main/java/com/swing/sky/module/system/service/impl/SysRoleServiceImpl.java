package com.swing.sky.module.system.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.module.system.dao.SysRoleDAO;
import com.swing.sky.module.system.dao.SysRoleDeptLinkDAO;
import com.swing.sky.module.system.dao.SysRoleMenuLinkDAO;
import com.swing.sky.module.system.dao.SysUserRoleLinkDAO;
import com.swing.sky.module.system.domain.SysRoleDO;
import com.swing.sky.module.system.domain.SysRoleDeptLinkDO;
import com.swing.sky.module.system.domain.SysRoleMenuLinkDO;
import com.swing.sky.module.system.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleDAO roleDAO;

    @Resource
    private SysRoleMenuLinkDAO roleMenuLinkDAO;

    @Resource
    private SysRoleDeptLinkDAO roleDeptLinkDAO;

    @Resource
    private SysUserRoleLinkDAO userRoleLinkDAO;

    @Override
    @SkyServiceAuthority(moduleName = "role")
    public int insert(SysRoleDO sysRoleDO) {
        //验证和已有信息是否冲突
        if (roleDAO.countRoleByRoleName(sysRoleDO.getRoleName(), -1L) > 0) {
            throw new RuntimeException("新增角色'" + sysRoleDO.getRoleName() + "'失败，角色名称已存在");
        }
        return roleDAO.insert(sysRoleDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "role")
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteById(Long id) {
        //校验是否有用户被分配了此角色
        if (userRoleLinkDAO.listOneIdsByTwoId(id).length > 0) {
            throw new RuntimeException("已有用户分配此角色，无法删除");
        }
        int i = roleDAO.deleteById(id);
        //删除与部门关联的信息
        int i1 = roleMenuLinkDAO.deleteItemByOneId(id);
        int i2 = roleDeptLinkDAO.deleteItemByOneId(id);
        return i + i1 + i2;
    }

    @Override
    @SkyServiceAuthority(moduleName = "role")
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchDeleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
        return 1;
    }

    @Override
    @SkyServiceAuthority(moduleName = "role")
    public int update(SysRoleDO sysRoleDO) {
        if (roleDAO.countRoleByRoleName(sysRoleDO.getRoleName(), sysRoleDO.getId()) > 0) {
            throw new RuntimeException("更新角色'" + sysRoleDO.getRoleName() + "'失败，角色名称已存在");
        }
        return roleDAO.update(sysRoleDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "role")
    public SysRoleDO getById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public List<SysRoleDO> listByCondition(SysRoleDO sysRoleDO, String beginTime, String endTime) {
        return roleDAO.listByCondition(sysRoleDO, beginTime, endTime);
    }

    @Override
    public List<SysRoleDO> listByConditionAndUserId(Long userId, SysRoleDO t, String beginTime, String endTime) {
        if (AdminUtils.isAdminUser(userId)) {
            return roleDAO.listByCondition(t, beginTime, endTime);
        }
        return roleDAO.listByConditionAndUserId(userId, t, beginTime, endTime);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateRoleMenuLink(Long roleId, Long[] menuIds) {
        roleMenuLinkDAO.deleteItemByOneId(roleId);
        return insertRoleMenu(roleId, menuIds);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateRoleDeptLink(Long roleId, Long[] deptIds) {
        roleDeptLinkDAO.deleteItemByOneId(roleId);
        return insertRoleDept(roleId, deptIds);
    }

    @Override
    public Long[] listDeptIdsByRoleId(Long roleId) {
        return roleDeptLinkDAO.listTwoIdsByOneId(roleId);
    }

    @Override
    public Long[] listMenuIdsByRoleId(Long menuId) {
        return roleMenuLinkDAO.listTwoIdsByOneId(menuId);
    }

    // -----------------------------------非接口方法--------------------------------------------

    /**
     * 插入角色菜单关联信息
     *
     * @param roleId  角色id
     * @param menuIds 菜单集合
     * @return 影响行数
     */
    public int insertRoleMenu(Long roleId, Long[] menuIds) {
        if (menuIds != null) {
            List<SysRoleMenuLinkDO> roleMenus = new ArrayList<>();
            for (Long menuId : menuIds) {
                SysRoleMenuLinkDO roleMenuLinkDO = new SysRoleMenuLinkDO();
                roleMenuLinkDO.setRoleId(roleId);
                roleMenuLinkDO.setMenuId(menuId);
                roleMenus.add(roleMenuLinkDO);
            }
            if (roleMenus.size() > 0) {
                int i = roleMenuLinkDAO.batchInsert(roleMenus);
                log.info("插入角色菜单关联信息结果:" + i);
                return i;
            }
        }
        return 0;
    }

    /**
     * 插入角色菜单关联信息
     *
     * @param roleId  角色id
     * @param deptIds 菜单集合
     * @return 影响行数
     */
    public int insertRoleDept(Long roleId, Long[] deptIds) {
        if (deptIds != null) {
            List<SysRoleDeptLinkDO> roleDeptList = new ArrayList<>();
            for (Long deptId : deptIds) {
                SysRoleDeptLinkDO roleDeptLinkDO = new SysRoleDeptLinkDO();
                roleDeptLinkDO.setRoleId(roleId);
                roleDeptLinkDO.setDeptId(deptId);
                roleDeptList.add(roleDeptLinkDO);
            }
            if (roleDeptList.size() > 0) {
                int i = roleDeptLinkDAO.batchInsert(roleDeptList);
                log.info("插入角色部门关联信息结果:" + i);
                return i;
            }
        }
        return 0;
    }
}
