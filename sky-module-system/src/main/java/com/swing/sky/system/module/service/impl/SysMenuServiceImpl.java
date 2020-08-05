package com.swing.sky.system.module.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.system.module.dao.SysMenuDAO;
import com.swing.sky.system.module.dao.SysRoleMenuLinkDAO;
import com.swing.sky.system.module.domain.SysMenuDO;
import com.swing.sky.system.module.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    private static final Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);
    @Resource
    private SysMenuDAO menuDAO;

    @Resource
    private SysRoleMenuLinkDAO roleMenuLinkDAO;


    @Override
    @SkyServiceAuthority(moduleName = "menu")
    public int insert(SysMenuDO sysMenuDO) {
        //判断菜单名是否存在（在同级目录下）
        if (menuDAO.countByParentIdAndMenuName(sysMenuDO.getParentId(), sysMenuDO.getMenuName(), -1L) > 0) {
            throw new RuntimeException("新增菜单'" + sysMenuDO.getMenuName() + "'失败，菜单名称已存在");
        }
        //判断父类菜单是否停用，如为停用则此菜单默认为停用
        if (sysMenuDO.getParentId() != 0L) {
            if (!menuDAO.getById(sysMenuDO.getParentId()).getUse()) {
                sysMenuDO.setUse(false);
            }
        }

        return menuDAO.insert(sysMenuDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "menu")
    public int deleteById(Long id) {
        if (menuDAO.countChildrenMenuById(id) > 0) {
            throw new RuntimeException("存在子菜单,不允许删除");
        }
        if (roleMenuLinkDAO.countItemByTwoId(id) > 0) {
            throw new RuntimeException("菜单已分配,不允许删除");
        }
        return menuDAO.deleteById(id);
    }

    /**
     * 此方法在该类忽略
     */
    @Override
    @SkyServiceAuthority(moduleName = "menu")
    public int batchDeleteByIds(Long[] ids) {
        return 0;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @SkyServiceAuthority(moduleName = "menu")
    public int update(SysMenuDO sysMenuDO) {
        //查询同级下是否有同名的菜单
        if (menuDAO.countByParentIdAndMenuName(sysMenuDO.getParentId(), sysMenuDO.getMenuName(), sysMenuDO.getId()) > 0) {
            throw new RuntimeException("更新菜单'" + sysMenuDO.getMenuName() + "'失败，菜单名称已存在");
        }
        //检查上级部门是否为自己
        if (sysMenuDO.getParentId().equals(sysMenuDO.getId())) {
            throw new RuntimeException("修改修改'" + sysMenuDO.getMenuName() + "'失败，上级菜单不能是自己");
        }
        //当部门状态从正常变为停用，其所有的子部门的状态都变为停用
        if (sysMenuDO.getUse().equals(false)) {
            updateChildrenMenuUseStatusOff(sysMenuDO, sysMenuDO.getUpdateBy());
        }
        //当部门状态从停用变为正常，其所有父级（多级）的状态都变为正常
        if (sysMenuDO.getUse().equals(true)) {
            updateParentMenuUseStatusOn(sysMenuDO, sysMenuDO.getUpdateBy());
        }
        return menuDAO.update(sysMenuDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "menu")
    public SysMenuDO getById(Long id) {
        return menuDAO.getById(id);
    }

    @Override
    public List<SysMenuDO> listByCondition(SysMenuDO sysMenuDO, String beginTime, String endTime) {
        return menuDAO.listByCondition(sysMenuDO, beginTime, endTime);
    }

    @Override
    public List<SysMenuDO> listByConditionAndUserId(Long userId, SysMenuDO t, String beginTime, String endTime) {
        if (AdminUtils.isAdminUser(userId)) {
            return menuDAO.listByCondition(t, beginTime, endTime);
        }
        return menuDAO.listByConditionAndUserId(userId, t, beginTime, endTime);
    }


    /**
     * -----------------------------------非接口方法--------------------------------------------
     * 停用该菜单下的所有菜单
     *
     * @param menu     当前菜单
     * @param updateBy 更新者
     */
    private void updateChildrenMenuUseStatusOff(SysMenuDO menu, String updateBy) {
        //如果该部门有子部门的话，继续递归
        if (menuDAO.countChildrenMenuById(menu.getId()) > 0) {
            SysMenuDO conditions = new SysMenuDO();
            conditions.setParentId(menu.getId());
            //获取子部门列表
            List<SysMenuDO> childrenMenu = menuDAO.listByCondition(conditions, null, null);
            for (SysMenuDO menuDO : childrenMenu) {
                menuDO.setUse(false);
                menuDO.setUpdateBy(updateBy);
                menuDAO.update(menuDO);
                updateChildrenMenuUseStatusOff(menuDO, updateBy);
            }
        }
    }

    /**
     * 启用该菜单的所有上级菜单
     *
     * @param menu     当前菜单
     * @param updateBy 更新者
     */
    private void updateParentMenuUseStatusOn(SysMenuDO menu, String updateBy) {
        //如果还有父部门的话，继续递归
        if (menu.getParentId() != 0L) {
            SysMenuDO parentMenu = menuDAO.getById(menu.getParentId());
            parentMenu.setUse(true);
            parentMenu.setUpdateBy(updateBy);
            menuDAO.update(parentMenu);
            updateParentMenuUseStatusOn(parentMenu, updateBy);
        }
    }


}
