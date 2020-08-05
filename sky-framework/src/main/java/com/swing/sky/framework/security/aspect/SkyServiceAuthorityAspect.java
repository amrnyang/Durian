package com.swing.sky.framework.security.aspect;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.basic.BasicDO;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.common.utils.ArrayUtils;
import com.swing.sky.framework.security.utils.UserDetailsUtil;
import com.swing.sky.module.system.domain.SysDeptDO;
import com.swing.sky.module.system.domain.SysMenuDO;
import com.swing.sky.module.system.service.SkipService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Service层权限范围预处理，判断访问的数据是否在拥有的权限条目中
 *
 * @author swing
 */
@Aspect
@Order(1)
@Component
public class SkyServiceAuthorityAspect {
    private SkipService skipService;

    @Autowired
    public void setSkipService(SkipService skipService) {
        this.skipService = skipService;
    }

    SkyServiceAuthority skyServiceAuthority;

    /**
     * 使用注解标识切点(方法级别或类级别）
     */
    @Pointcut("@annotation(com.swing.sky.common.annotation.SkyServiceAuthority)" + "|| @within(com.swing.sky.common.annotation.SkyServiceAuthority)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        skyServiceAuthority = getAnnotation(point);
        if (skyServiceAuthority != null) {
            //获取该模块可访问的数据id列表和该注解方法名
            String moduleName = skyServiceAuthority.moduleName();
            String methodName = getMethodName(point);
            //如果不为管理员的话，校验权限范围
            if (!AdminUtils.isAdminUser(UserDetailsUtil.getUserId())) {
                Long[] list = listByUserId(moduleName, UserDetailsUtil.getUserId());
                handleAuthority(moduleName, list, methodName, point);
            }
            //所有用户都不能删除管理员用户和管理员角色（包括啊管理员本人）
            handleAdminCheck(moduleName, methodName, point);
            //为新增和更新内容添加公共字段
            addBasicInfo(methodName, point);
        }
        return point.proceed();
    }

    /**
     * 针对不同的方法指定不同的校验逻辑
     *
     * @param moduleName 模块名
     * @param list       数据范围
     * @param methodName 方法名
     * @param point      切点
     */
    public void handleAuthority(String moduleName, Long[] list, String methodName, JoinPoint point) {
        if (methodName.equalsIgnoreCase(ModuleConstants.INSERT)) {
            checkInsert(moduleName, list, point);
        } else if (methodName.equalsIgnoreCase(ModuleConstants.DELETE_BY_ID)) {
            checkDeleteById(list, point);
        } else if (methodName.equalsIgnoreCase(ModuleConstants.BATCH_DELETE_BY_IDS)) {
            checkBatchDeleteByIds(list, point);
        } else if (methodName.equalsIgnoreCase(ModuleConstants.UPDATE)) {
            checkUpdate(list, point);
        } else if (methodName.equalsIgnoreCase(ModuleConstants.GET_BY_ID)) {
            checkGetById(list, point);
        }
    }

    /**
     * 执行管理员信息校验，防止管理员用户或角色被操作
     *
     * @param moduleName 模块名
     * @param methodName 方法名
     * @param point      切入点
     */
    public void handleAdminCheck(String moduleName, String methodName, JoinPoint point) {
        if (methodName.equalsIgnoreCase(ModuleConstants.DELETE_BY_ID)) {
            //获取deleteById的参数
            Long id = (Long) point.getArgs()[0];
            checkAdminId(moduleName, id);
        } else if (methodName.equalsIgnoreCase(ModuleConstants.BATCH_DELETE_BY_IDS)) {
            //获取batchDeleteByIds的参数
            Long[] ids = (Long[]) point.getArgs()[0];
            checkAdminId(moduleName, ids);
        } else if (methodName.equalsIgnoreCase(ModuleConstants.UPDATE)) {
            //获取update的参数
            BasicDO basicDO = (BasicDO) point.getArgs()[0];
            checkAdminId(moduleName, basicDO.getId());
        }
    }


    /**
     * 获取切点的注解
     */
    public SkyServiceAuthority getAnnotation(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        SkyServiceAuthority skyServiceAuthority = targetClass.getAnnotation(SkyServiceAuthority.class);
        if (skyServiceAuthority != null) {
            return skyServiceAuthority;
        } else {
            Method method = signature.getMethod();
            return method.getAnnotation(SkyServiceAuthority.class);
        }
    }

    /**
     * 获取注解的方法名
     */
    public String getMethodName(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }

    /**
     * 根据用户id列出该用户可以在该模块下访问的数据集合
     *
     * @param moduleName 模块名
     * @param userId     用户id
     * @return 数据id数组
     */
    public Long[] listByUserId(String moduleName, Long userId) {
        switch (moduleName) {
            case ModuleConstants.USER:
                return skipService.getUserIdsByUserId(userId);
            case ModuleConstants.ROLE:
                return skipService.getRoleIdsByUserId(userId);
            case ModuleConstants.MENU:
                return skipService.getMenuIdsByUserId(userId);
            case ModuleConstants.DEPT:
                return skipService.getDeptIdsByUserId(userId);
            case ModuleConstants.POST:
                return skipService.getPostIdsByUserId(userId);
            default:
                return null;
        }
    }

    public void addBasicInfo(String methodName, JoinPoint point) {
        if (methodName.equalsIgnoreCase(ModuleConstants.INSERT)) {
            BasicDO basicDO = (BasicDO) point.getArgs()[0];
            basicDO.setCreateBy(UserDetailsUtil.getUsername());
            basicDO.setCreateTime(new Date());
            basicDO.setUpdateBy(UserDetailsUtil.getUsername());
            basicDO.setUpdateTime(new Date());
        } else if (methodName.equalsIgnoreCase(ModuleConstants.UPDATE)) {
            BasicDO basicDO = (BasicDO) point.getArgs()[0];
            basicDO.setUpdateTime(new Date());
            basicDO.setUpdateBy(UserDetailsUtil.getUsername());
        }

    }

    /**
     * 检查用户操作的id是否为管理员用户或者角色
     *
     * @param moduleName 模块名
     * @param id         id
     */
    public void checkAdminId(String moduleName, Long id) {
        //不能删除管理员用户
        if (moduleName.equalsIgnoreCase(ModuleConstants.USER)) {
            if (AdminUtils.isAdminUser(id)) {
                throw new RuntimeException("不允许操作管理员用户");
            }
        }
        //不能删除管理员角色
        if (moduleName.equalsIgnoreCase(ModuleConstants.ROLE)) {
            if (AdminUtils.isAdminRole(id)) {
                throw new RuntimeException("不允许操作管理员角色");
            }
        }
    }

    /**
     * 检查用户操作的ids是否为管理员用户或者角色
     *
     * @param moduleName 模块名
     * @param ids        id
     */
    public void checkAdminId(String moduleName, Long[] ids) {
        //不能删除管理员用户
        if (moduleName.equalsIgnoreCase(ModuleConstants.USER)) {
            if (AdminUtils.isAdminUser(ids)) {
                throw new RuntimeException("不允许操作管理员用户");
            }
        }
        //不能删除管理员角色
        if (moduleName.equalsIgnoreCase(ModuleConstants.ROLE)) {
            if (AdminUtils.isAdminRole(ids)) {
                throw new RuntimeException("不允许操作管理员角色");
            }
        }
    }

    /**
     * 校验insert方法
     *
     * @param moduleName 模块名
     * @param list       资源拥有列表
     * @param point      切入点
     */
    public void checkInsert(String moduleName, Long[] list, JoinPoint point) {
        //如果是新增部门信息,校验其父部门是否在权限范围内
        if (moduleName.equalsIgnoreCase(ModuleConstants.DEPT)) {
            SysDeptDO sysDeptDO = (SysDeptDO) point.getArgs()[0];
            if (!ArrayUtils.contains(list, sysDeptDO.getParentId())) {
                throw new RuntimeException("你没有权限在该部门下新增部门");
            }
        }
        //如果是新增菜单信息,校验其父菜单是否在权限范围内
        if (moduleName.equalsIgnoreCase(ModuleConstants.MENU)) {
            SysMenuDO sysMenuDO = (SysMenuDO) point.getArgs()[0];
            if (!ArrayUtils.contains(list, sysMenuDO.getParentId())) {
                throw new RuntimeException("你没有权限在该菜单下新增菜单");
            }
        }
    }

    /**
     * 校验deleteById方法
     *
     * @param list  资源拥有列表
     * @param point 切入点
     */
    public void checkDeleteById(Long[] list, JoinPoint point) {
        //获取deleteById的参数
        Long id = (Long) point.getArgs()[0];
        if (!ArrayUtils.contains(list, id)) {
            throw new RuntimeException("你操作的数据不在你拥有的权限范围内");
        }
    }

    /**
     * 校验batchDeleteByIds方法
     *
     * @param list  资源拥有列表
     * @param point 切入点
     */
    public void checkBatchDeleteByIds(Long[] list, JoinPoint point) {
        //获取batchDeleteByIds的参数
        Long[] ids = (Long[]) point.getArgs()[0];
        if (!ArrayUtils.contains(list, ids)) {
            throw new RuntimeException("你操作的数据不在你拥有的权限范围内");
        }
    }

    /**
     * 校验update方法
     *
     * @param list  资源拥有列表
     * @param point 切入点
     */
    public void checkUpdate(Long[] list, JoinPoint point) {
        //获取update的参数
        BasicDO basicDO = (BasicDO) point.getArgs()[0];
        if (!ArrayUtils.contains(list, basicDO.getId()) && !UserDetailsUtil.getUserId().equals(basicDO.getId())) {
            throw new RuntimeException("你操作的数据不在你拥有的权限范围内");
        }
    }

    /**
     * 校验getById方法
     *
     * @param list  资源拥有列表
     * @param point 切入点
     */
    public void checkGetById(Long[] list, JoinPoint point) {
        //获取getById的参数
        Long id = (Long) point.getArgs()[0];
        if (!ArrayUtils.contains(list, id)) {
            throw new RuntimeException("你操作的数据不在你拥有的权限范围内");
        }
    }

}





































