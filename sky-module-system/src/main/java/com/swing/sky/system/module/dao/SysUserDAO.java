package com.swing.sky.system.module.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.system.module.domain.SysUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swing
 * 筛选字段：
 * deptId:部门id
 * username: 用户名
 * phone:电话
 * gender:性别
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysUserDAO extends BasicDAO<SysUserDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysUserDO> listByConditionAndUserId(@Param("userId") Long userId, @Param("condition") SysUserDO t, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUserDO getUserByUsername(String username);

    /**
     * 列出部门列表下的所有用户id
     *
     * @param deptIds 部门id列表
     * @return 用户id列表
     */
    Long[] listIdsByDeptIds(Long[] deptIds);

    /**
     * 列出部门列表下的所有用户列表
     *
     * @param deptIds 部门id列表
     * @return 用户列表
     */
    List<SysUserDO> listByDeptIds(Long[] deptIds);

    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @param id       用户id
     * @return 结果
     */
    int countByUserName(@Param("userName") String userName, @Param("id") Long id);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 手机号码
     * @param id    用户id1
     * @return 结果
     */
    int countByPhone(@Param("phone") String phone, @Param("id") Long id);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @param id    用户id
     * @return 结果
     */
    int countByEmail(@Param("email") String email, @Param("id") Long id);

}