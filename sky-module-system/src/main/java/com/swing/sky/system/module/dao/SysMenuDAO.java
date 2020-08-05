package com.swing.sky.system.module.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.system.module.domain.SysMenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swing
 * 筛选字段：
 * menuName:菜单名
 * menuType:菜单类型
 * externalLink:是否为外链接
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysMenuDAO extends BasicDAO<SysMenuDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysMenuDO> listByConditionAndUserId(@Param("userId") Long userId, @Param("condition") SysMenuDO t, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询子菜单数量
     *
     * @param id 菜单ID
     * @return 结果
     */
    int countChildrenMenuById(Long id);

    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验统一层级的菜单名是否唯一
     *
     * @param parentId 父菜单ID
     * @param menuName 菜单名称
     * @param id       菜单id
     * @return 结果
     */
    int countByParentIdAndMenuName(@Param("parentId") Long parentId, @Param("menuName") String menuName, @Param("id") Long id);
}