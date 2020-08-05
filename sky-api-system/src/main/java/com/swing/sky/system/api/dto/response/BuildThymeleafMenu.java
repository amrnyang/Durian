package com.swing.sky.system.api.dto.response;

import com.swing.sky.common.basic.BasicDO;
import com.swing.sky.system.api.dto.response.menu.ThymeleafMenu;
import com.swing.sky.system.module.domain.SysMenuDO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 构建thymeleaf需要的菜单结构
 *
 * @author swing
 */
public class BuildThymeleafMenu {

    /**
     * 构建thymeleaf需要的菜单结构
     *
     * @param parentId 父节点
     * @param menus    菜单列表
     * @return 结果
     */
    public static List<ThymeleafMenu> build(Long parentId, List<SysMenuDO> menus) {
        List<ThymeleafMenu> thymeleafMenus = new ArrayList<>();
        for (SysMenuDO menu : menus) {
            if (menu.getParentId().equals(parentId)) {
                ThymeleafMenu thymeleafMenu = new ThymeleafMenu(menu);
                //列出该菜单的所子菜单
                List<SysMenuDO> childrenMenus = new ArrayList<>();
                for (SysMenuDO menuDO : menus) {
                    if (menuDO.getParentId().equals(menu.getId()) && !"B".equals(menuDO.getMenuType())) {
                        childrenMenus.add(menuDO);
                    }
                }
                //如果子菜单不为空
                if (childrenMenus.size() > 0) {
                    List<ThymeleafMenu> childrenList = build(thymeleafMenu.getId(), menus);
                    //对子菜单依据orderNum进行排
                    List<ThymeleafMenu> childrenMenusOrder = childrenList.stream().sorted(Comparator.comparingInt(BasicDO::getOrderNum)).collect(Collectors.toList());
                    thymeleafMenu.setChildren(childrenMenusOrder);
                } else {
                    thymeleafMenu.setChildren(null);
                }
                thymeleafMenus.add(thymeleafMenu);
            }
        }
        return thymeleafMenus;
    }
}
