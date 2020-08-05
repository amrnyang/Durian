package com.swing.sky.system.module.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.system.module.dao.SysDeptDAO;
import com.swing.sky.system.module.dao.SysPostDeptLinkDAO;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.domain.SysPostDeptLinkDO;
import com.swing.sky.system.module.service.SysDeptService;
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
public class SysDeptServiceImpl implements SysDeptService {
    private static final Logger log = LoggerFactory.getLogger(SysDeptServiceImpl.class);
    @Resource
    private SysDeptDAO deptDAO;

    @Resource
    private SysPostDeptLinkDAO postDeptLinkDAO;

    @Override
    @SkyServiceAuthority(moduleName = "dept")
    public int insert(SysDeptDO sysDeptDO) {
        //验证部门名称是否冲突
        if (deptDAO.countByParentIdAndDeptName(sysDeptDO.getParentId(), sysDeptDO.getDeptName(), -1L) > 0) {
            throw new RuntimeException("新增部门'" + sysDeptDO.getDeptName() + "'失败，该部门下存在同名的部门信息");
        }
        return deptDAO.insert(sysDeptDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "dept")
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteById(Long id) {
        int i = deptDAO.deleteById(id);
        //删除部门与岗位的关联
        int i1 = postDeptLinkDAO.deleteItemByTwoId(id);
        return i + i1;
    }

    @Override
    @SkyServiceAuthority(moduleName = "dept")
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchDeleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
        return 1;
    }

    @Override
    @SkyServiceAuthority(moduleName = "dept")
    public int update(SysDeptDO sysDeptDO) {
        //验证部门名称是否冲突
        if (deptDAO.countByParentIdAndDeptName(sysDeptDO.getParentId(), sysDeptDO.getDeptName(), sysDeptDO.getId()) > 0) {
            throw new RuntimeException("更新部门'" + sysDeptDO.getDeptName() + "'失败，该部门下存在同名的部门信息");
        }
        //如果启用，则启用所有的上级部门
        if (sysDeptDO.getUse()) {
            updateParentDeptUseStatusOn(sysDeptDO);
        }
        //如果停用，则停用该部门下的所有部门
        if (!sysDeptDO.getUse()) {
            updateChildrenDeptUseStatusOff(sysDeptDO);
        }
        return deptDAO.update(sysDeptDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "dept")
    public SysDeptDO getById(Long id) {
        return deptDAO.getById(id);
    }

    @Override
    public List<SysDeptDO> listByCondition(SysDeptDO sysDeptDO, String beginTime, String endTime) {
        return deptDAO.listByCondition(sysDeptDO, beginTime, endTime);
    }

    @Override
    public List<SysDeptDO> listByConditionAndUserId(Long userId, SysDeptDO t, String beginTime, String endTime) {
        if (AdminUtils.isAdminUser(userId)) {
            return deptDAO.listByCondition(t, beginTime, endTime);
        }
        return deptDAO.listByConditionAndUserId(userId, t, beginTime, endTime);
    }


    @Override
    public Long[] listPostIdsByDeptId(Long deptId) {
        return postDeptLinkDAO.listOneIdsByTwoId(deptId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateDeptPostLink(Long deptId, Long[] postIds) {
        //删除并插入部门岗位信息关联
        postDeptLinkDAO.deleteItemByTwoId(deptId);
        return insertDeptPost(deptId, postIds);
    }

    // -----------------------------------非接口方法--------------------------------------------

    /**
     * 插入部门岗位关联信息
     *
     * @param deptId  部门id
     * @param postIds 岗位集合
     * @return 影响行数
     */
    public int insertDeptPost(Long deptId, Long[] postIds) {
        List<SysPostDeptLinkDO> postDeptList = new ArrayList<>();
        for (Long postId : postIds) {
            SysPostDeptLinkDO postDeptLinkDO = new SysPostDeptLinkDO();
            postDeptLinkDO.setPostId(postId);
            postDeptLinkDO.setDeptId(deptId);
            postDeptList.add(postDeptLinkDO);
        }
        if (postDeptList.size() > 0) {
            int i = postDeptLinkDAO.batchInsert(postDeptList);
            log.info("插入岗位部门关联信息结果:" + i);
            return i;
        }
        return 0;
    }

    /**
     * 停用该部门下的所有子部门
     *
     * @param dept 当前部门
     */
    private void updateChildrenDeptUseStatusOff(SysDeptDO dept) {
        //如果该部门有子部门的话，继续递归
        SysDeptDO conditions = new SysDeptDO();
        conditions.setParentId(dept.getId());
        List<SysDeptDO> children = deptDAO.listByCondition(conditions, null, null);
        if (children != null && children.size() > 0) {
            for (SysDeptDO child : children) {
                child.setUse(false);
                deptDAO.update(child);
                updateChildrenDeptUseStatusOff(child);
            }
        }
    }

    /**
     * 启用该部门的所有上级部门
     *
     * @param dept 当前菜单
     */
    private void updateParentDeptUseStatusOn(SysDeptDO dept) {
        //如果还有父部门的话，继续递归
        if (dept.getParentId() != 0L) {
            SysDeptDO parentDept = deptDAO.getById(dept.getParentId());
            parentDept.setUse(true);
            deptDAO.update(parentDept);
            updateParentDeptUseStatusOn(parentDept);
        }
    }
}


























