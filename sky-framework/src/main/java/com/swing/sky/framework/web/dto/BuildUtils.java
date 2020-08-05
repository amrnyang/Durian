//package com.swing.sky.framework.web.utils;
//
//import com.swing.sky.common.constant.MenuTypeConstants;
//import com.swing.sky.common.utils.StringUtils;
//import com.swing.sky.framework.web.dto.response.router.Meta;
//import com.swing.sky.framework.web.dto.response.router.RouterDTO;
//import com.swing.sky.framework.web.dto.response.tree.SelectTreeDTO;
//import com.swing.sky.framework.web.dto.response.tree.SelectTreeNode;
//import com.swing.sky.framework.web.dto.response.tree.ShowTreeDTO;
//import com.swing.sky.framework.web.dto.response.tree.ShowTreeNode;
//import SysMenuDO;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 自定义数据结构构建工具
// *
// * @author swing
// */
//public class BuildUtils {
//
//    /**
//     * 构建前端路由所需要的路由结构
//     *
//     * @return 路由列表
//     */
//    public static List<RouterDTO> buildRouterDTO(List<SysMenuDO> menuList) {
//        List<RouterDTO> directories = new ArrayList<>();
//        for (SysMenuDO menu : menuList) {
//            //找出该菜单列表中最上层目录
//            if (menu.getParentId().equals(0L)) {
//                RouterDTO routerDTO = new RouterDTO();
//                buildDirectoryDTO(routerDTO, menu, menuList);
//                directories.add(routerDTO);
//            }
//        }
//        return directories;
//    }
//
//    /**
//     * 递归构建菜单
//     *
//     * @param routerDTO 菜单视图
//     * @param menu      菜单对象
//     * @param menus     菜单列表
//     */
//    public static void buildDirectoryDTO(RouterDTO routerDTO, SysMenuDO menu, List<SysMenuDO> menus) {
//        routerDTO.setName(StringUtils.capitalize(menu.getUrl()));
//        if (menu.getExternalLink()) {
//            routerDTO.setPath(menu.getUrl());
//        } else {
//            routerDTO.setPath("/" + menu.getUrl());
//        }
//        routerDTO.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
//        routerDTO.setMeta(new Meta(menu.getMenuName(), menu.getIcon()));
//        //如果是目录的话，增加额外的字段
//        if (menu.getMenuType().equals(MenuTypeConstants.MENU_TYPE_DIRECTORY)) {
//            routerDTO.setAlwaysShow(true);
//            routerDTO.setRedirect("noRedirect");
//
//            //获取该目录的子菜单
//            List<SysMenuDO> childMenus = new ArrayList<>();
//            for (SysMenuDO menuDO : menus) {
//                if (menuDO.getParentId().equals(menu.getMenuId())) {
//                    childMenus.add(menuDO);
//                }
//            }
//            if (childMenus.size() > 0) {
//                List<RouterDTO> menuList = new ArrayList<>();
//                for (SysMenuDO menuDO : childMenus) {
//                    RouterDTO directory = new RouterDTO();
//                    buildDirectoryDTO(directory, menuDO, menus);
//                    menuList.add(directory);
//                }
//                routerDTO.setChildren(menuList);
//            }
//        }
//    }
//
//    /**
//     * 构建选择树结构的数据
//     *
//     * @return 选择树集合
//     */
//    public static List<SelectTreeDTO> buildSelectTreeVO(List<SelectTreeNode> selectTreeNodes) {
//        List<SelectTreeDTO> selectTreeDTOList = new ArrayList<>();
//        for (SelectTreeNode selectTreeNode : selectTreeNodes) {
//            //找到顶部节点
//            if (selectTreeNode.getParentId().equals(0L)) {
//                SelectTreeDTO selectTreeDTO = new SelectTreeDTO();
//                selectTreeDTO.setId(selectTreeNode.getId());
//                selectTreeDTO.setLabel(selectTreeNode.getNodeName());
//                buildChildSelectTreeVO(selectTreeDTO, selectTreeNodes);
//                selectTreeDTOList.add(selectTreeDTO);
//            }
//        }
//        return selectTreeDTOList;
//    }
//
//    /**
//     * 递归构建子选择树
//     */
//    public static void buildChildSelectTreeVO(SelectTreeDTO selectTreeDTO, List<SelectTreeNode> selectTreeNodes) {
//        List<SelectTreeDTO> children = new ArrayList<>();
//        for (SelectTreeNode selectTreeNode : selectTreeNodes) {
//            if (selectTreeNode.getParentId().equals(selectTreeDTO.getId())) {
//                SelectTreeDTO childSelectTreeDTO = new SelectTreeDTO();
//                childSelectTreeDTO.setId(selectTreeNode.getId());
//                childSelectTreeDTO.setLabel(selectTreeNode.getNodeName());
//                for (SelectTreeNode selectTreeNode1 : selectTreeNodes) {
//                    //如果该节点还有子节点,继续递归
//                    if (selectTreeNode1.getParentId().equals(selectTreeNode.getId())) {
//                        buildChildSelectTreeVO(childSelectTreeDTO, selectTreeNodes);
//                    }
//                }
//                children.add(childSelectTreeDTO);
//            }
//        }
//        selectTreeDTO.setChildren(children);
//    }
//
//
//    /**
//     * 构建显示树结构的数据
//     *
//     * @return 展示树
//     */
//    public static List<ShowTreeDTO> buildShowTreeVO(List<ShowTreeNode> showTreeNodes) {
//        List<ShowTreeDTO> showTreeDTOList = new ArrayList<>();
//        for (ShowTreeNode showTreeNode : showTreeNodes) {
//            //找到顶部节点
//            if (showTreeNode.getParentId().equals(0L)) {
//                ShowTreeDTO showTreeDTO = new ShowTreeDTO();
//                showTreeDTO.setId(showTreeNode.getId());
//                showTreeDTO.setNodeName(showTreeNode.getNodeName());
//                showTreeDTO.setUse(showTreeNode.getUse());
//                showTreeDTO.setCreateBy(showTreeNode.getCreateBy());
//                showTreeDTO.setCreateTime(showTreeNode.getCreateTime());
//                buildChildShowTreeVO(showTreeDTO, showTreeNodes);
//                showTreeDTOList.add(showTreeDTO);
//            }
//        }
//        return showTreeDTOList;
//    }
//
//    /**
//     * 递归构建子显示树
//     */
//    public static void buildChildShowTreeVO(ShowTreeDTO showTreeDTO, List<ShowTreeNode> showTreeNodes) {
//        List<ShowTreeDTO> children = new ArrayList<>();
//        for (ShowTreeNode showTreeNode : showTreeNodes) {
//            if (showTreeNode.getParentId().equals(showTreeDTO.getId())) {
//                ShowTreeDTO childShowTreeDTO = new ShowTreeDTO();
//                childShowTreeDTO.setId(showTreeNode.getId());
//                childShowTreeDTO.setNodeName(showTreeNode.getNodeName());
//                childShowTreeDTO.setUse(showTreeNode.getUse());
//                childShowTreeDTO.setCreateBy(showTreeNode.getCreateBy());
//                childShowTreeDTO.setCreateTime(showTreeNode.getCreateTime());
//                for (ShowTreeNode selectTreeNode1 : showTreeNodes) {
//                    //如果该节点还有子节点,继续递归
//                    if (selectTreeNode1.getParentId().equals(showTreeNode.getId())) {
//                        buildChildShowTreeVO(childShowTreeDTO, showTreeNodes);
//                    }
//                }
//                children.add(childShowTreeDTO);
//            }
//        }
//        showTreeDTO.setChildren(children);
//    }
//
//}
