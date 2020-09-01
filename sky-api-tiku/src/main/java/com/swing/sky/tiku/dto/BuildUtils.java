package com.swing.sky.tiku.dto;

import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.tiku.module.domain.TiCourseDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建构建返回前端的工具类的工具类
 *
 * @author swing
 */
public class BuildUtils {

    /**
     * 构建课程选择树
     *
     * @param deptDOList 部门列表
     * @param courseList 课程列表
     * @return 结果
     */
    public static List<TreeDTO> buildCourseSelectTree(List<SysDeptDO> deptDOList, List<TiCourseDO> courseList) {
        if (deptDOList != null && courseList != null) {
            List<TreeDTO> trees = new ArrayList<>();
            for (SysDeptDO deptDO : deptDOList) {
                TreeDTO tree = new TreeDTO();
                //加入100000L解决专业表和课程表的id冲突bug
                tree.setId(deptDO.getId()+100000L);
                tree.setpId(deptDO.getParentId());
                tree.setName(deptDO.getDeptName());
                tree.setTitle(deptDO.getDeptName());
                trees.add(tree);
            }
            for (TiCourseDO course : courseList) {
                TreeDTO tree = new TreeDTO();
                tree.setId(course.getId());
                tree.setpId(course.getDeptId()+100000L);
                tree.setName(course.getCourseName());
                tree.setTitle(course.getCourseName());
                trees.add(tree);
            }
            return trees;
        }
        return null;
    }

}
