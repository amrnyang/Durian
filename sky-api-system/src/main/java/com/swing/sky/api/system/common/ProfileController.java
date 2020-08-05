package com.swing.sky.api.system.common;

import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.framework.security.utils.UserDetailsUtil;
import com.swing.sky.framework.web.SkyResponse;
import com.swing.sky.api.system.BasicController;
import com.swing.sky.module.system.dao.SysUserPostLinkDAO;
import com.swing.sky.module.system.dao.SysUserRoleLinkDAO;
import com.swing.sky.module.system.domain.SysPostDO;
import com.swing.sky.module.system.domain.SysRoleDO;
import com.swing.sky.module.system.domain.SysUserDO;
import com.swing.sky.module.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 个人中心
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("profile")
public class ProfileController extends BasicController {
    private SysUserService userService;


    @Resource
    private SysUserPostLinkDAO userPostLinkDAO;

    @Resource
    private SysUserRoleLinkDAO userRoleLinkDAO;

    @Autowired
    public void setUserService(SysUserService userService) {
        this.userService = userService;
    }


    /**
     * 个人信息(视图）
     */
    @GetMapping()
    public String profile(Model model) {
        SysUserDO user = UserDetailsUtil.getUserDO();
        model.addAttribute("user", user);
        List<SysRoleDO> roleList = userRoleLinkDAO.listTwoByOneId(user.getId());
        StringBuilder roles = new StringBuilder();
        for (SysRoleDO role : roleList) {
            if (role != null) {
                roles.append(role.getRoleName()).append(",");
            }
        }
        String roleGroup = roles.toString();
        List<SysPostDO> postList = userPostLinkDAO.listTwoByOneId(user.getId());
        StringBuilder posts = new StringBuilder();
        for (SysPostDO post : postList) {
            if (post != null) {
                posts.append(post.getPostName()).append(",");
            }
        }
        String postGroup = posts.toString();
        model.addAttribute("roleGroup", roleGroup);
        model.addAttribute("postGroup", postGroup);
        return "system/user/profile/profile";
    }

    /**
     * 更新个人基本信息
     */
    @PostMapping("/update")
    @ResponseBody
    @OperateLog(module = ModuleConstants.PROFILE, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse updateProfile(SysUserDO user) {
        //新建用户对象，限制用户可更新的内容
        SysUserDO userDO = new SysUserDO();
        userDO.setId(UserDetailsUtil.getUserId());
        userDO.setUsername(user.getUsername());
        userDO.setPhone(user.getPhone());
        userDO.setEmail(user.getEmail());
        userDO.setGender(user.getGender());
        userService.update(userDO);
        return SkyResponse.success("基本信息更新成功！");
    }

    /**
     * 修改密码
     */
    @PostMapping("/resetPwd")
    @ResponseBody
    @OperateLog(module = ModuleConstants.PROFILE, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse resetPassword(String oldPassword, String newPassword) {
        SysUserDO userDO = UserDetailsUtil.getUserDO();
        //校验原密码是否正确
        String password = userDO.getPassword();
        if (!new BCryptPasswordEncoder().matches(oldPassword, password)) {
            throw new RuntimeException("原密码不正确，请重新输入");
        }
        userDO.setPassword(newPassword);
        userService.update(userDO);
        return SkyResponse.success("密码修改成功！");
    }
}


























