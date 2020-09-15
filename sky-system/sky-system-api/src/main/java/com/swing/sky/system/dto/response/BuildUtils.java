package com.swing.sky.system.dto.response;

import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.common.utils.ArrayUtils;
import com.swing.sky.system.dto.response.choice.PostSelectedDTO;
import com.swing.sky.system.dto.response.choice.RoleSelectedDTO;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.domain.SysMenuDO;
import com.swing.sky.system.module.domain.SysPostDO;
import com.swing.sky.system.module.domain.SysRoleDO;
//import com.swing.sky.tiku.module.domain.TiCourseDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建选择树结构数据的工具类
 *
 * @author swing
 */
public class BuildUtils {
    /**
     * 构建菜单选择树
     */
    public static List<TreeDTO> buildMenuSelectTree(List<SysMenuDO> menuDOList) {
        if (menuDOList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (SysMenuDO menuDO : menuDOList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(menuDO.getId());
                tree.setpId(menuDO.getParentId());
                tree.setName(menuDO.getMenuName());
                tree.setTitle(menuDO.getMenuName());
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

    /**
     * 构建部门选择树
     */
    public static List<TreeDTO> buildDeptSelectTree(List<SysDeptDO> deptDOList) {
        if (deptDOList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (SysDeptDO deptDO : deptDOList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(deptDO.getId());
                tree.setpId(deptDO.getParentId());
                tree.setName(deptDO.getDeptName());
                tree.setTitle(deptDO.getDeptName());
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

    /**
     * 构建部门选择树
     */
    public static List<TreeDTO> buildCenterDeptSelectTree(List<CenterDeptDO> deptDOList) {
        if (deptDOList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (CenterDeptDO deptDO : deptDOList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(deptDO.getId());
                tree.setpId(deptDO.getParentId());
                tree.setName(deptDO.getDeptName());
                tree.setTitle(deptDO.getDeptName());
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

    /**
     * 构建课程选择树
     *
     * @param deptDOList 部门列表
     * @param courseList 课程列表
     * @return 结果
     */
    public static List<TreeDTO> buildCourseSelectTree(List<CenterDeptDO> deptDOList, List<CenterCourseDO> courseList) {
        if (deptDOList != null && courseList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (CenterDeptDO deptDO : deptDOList) {
                TreeDTO tree = new TreeDTO();
                //加入100000L解决专业表和课程表的id冲突bug
                tree.setId(deptDO.getId() + 100000L);
                tree.setpId(deptDO.getParentId());
                tree.setName(deptDO.getDeptName());
                tree.setTitle(deptDO.getDeptName());
                trees.add(tree);
            }
            for (CenterCourseDO course : courseList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(course.getId());
                tree.setpId(course.getCollegeId() + 100000L);
                tree.setName(course.getCourseName());
                tree.setTitle(course.getCourseName());
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

    /**
     * 构建菜单选择树（已选择的）
     *
     * @param menuDOList 所有该用户可拥有的列表
     * @param checkIds   已选择的id列表
     */
    public static List<TreeDTO> buildMenuSelectedTree(List<SysMenuDO> menuDOList, Long[] checkIds) {
        if (menuDOList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (SysMenuDO menuDO : menuDOList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(menuDO.getId());
                tree.setpId(menuDO.getParentId());
                tree.setName(menuDO.getMenuName());
                tree.setTitle(menuDO.getMenuName());
                //如果该节点被选中的话
                if (ArrayUtils.contains(checkIds, menuDO.getId())) {
                    tree.setChecked(true);
                }
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

    /**
     * 构建部门选择树(已选择的）
     *
     * @param deptDOList 所有该用户可拥有的列表
     * @param checkIds   已选择的id列表
     */
    public static List<TreeDTO> buildDeptSelectedTree(List<SysDeptDO> deptDOList, Long[] checkIds) {
        if (deptDOList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (SysDeptDO deptDO : deptDOList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(deptDO.getId());
                tree.setpId(deptDO.getParentId());
                tree.setName(deptDO.getDeptName());
                tree.setTitle(deptDO.getDeptName());
                //如果该节点被选中的话
                if (ArrayUtils.contains(checkIds, deptDO.getId())) {
                    tree.setChecked(true);
                }
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

    /**
     * 构建岗位选择信息
     *
     * @param postDOList 所有该用户可以拥有的列表
     * @param checkIds   已选择的id列表
     * @return 结果
     */
    public static List<PostSelectedDTO> buildPostSelectedList(List<SysPostDO> postDOList, Long[] checkIds) {
        List<PostSelectedDTO> list = new ArrayList<>();
        for (SysPostDO postDO : postDOList) {
            PostSelectedDTO postSelectedDTO = new PostSelectedDTO(postDO);
            if (ArrayUtils.contains(checkIds, postDO.getId())) {
                postSelectedDTO.setChecked(true);
            }
            list.add(postSelectedDTO);
        }
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    /**
     * 构建部门选择信息
     *
     * @param roleDOList 所有该用户可以拥有的列表
     * @param checkIds   已选择的id列表
     * @return 结果
     */
    public static List<RoleSelectedDTO> buildRoleSelectedList(List<SysRoleDO> roleDOList, Long[] checkIds) {
        List<RoleSelectedDTO> list = new ArrayList<>();
        for (SysRoleDO roleDO : roleDOList) {
            RoleSelectedDTO roleSelectedDTO = new RoleSelectedDTO(roleDO);
            if (ArrayUtils.contains(checkIds, roleDO.getId())) {
                roleSelectedDTO.setChecked(true);
            }
            list.add(roleSelectedDTO);
        }
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }


}
