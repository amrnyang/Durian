package com.swing.sky.system.api.common;

import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.common.utils.aliyun.oss.AliyunUploadUtils;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.dao.SysUserPostLinkDAO;
import com.swing.sky.system.module.dao.SysUserRoleLinkDAO;
import com.swing.sky.system.module.domain.SysPostDO;
import com.swing.sky.system.module.domain.SysRoleDO;
import com.swing.sky.system.module.domain.SysUserDO;
import com.swing.sky.system.module.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        //从数据库中获取最新的用户信息
        SysUserDO user = userService.getById(UserDetailsUtil.getUserId());
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
        //从数据库中获取最新的用户信息
        SysUserDO userDO = userService.getById(UserDetailsUtil.getUserId());
        //校验原密码是否正确
        String password = userDO.getPassword();
        if (!new BCryptPasswordEncoder().matches(oldPassword, password)) {
            throw new RuntimeException("原密码不正确，请重新输入");
        }
        //密码加密保存
        userDO.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userService.update(userDO);
        return SkyResponse.success("密码修改成功！");
    }


    /**
     * 获取头像修改界面
     */
    @GetMapping("/avatar")
    public String avatar(Model model) {
        SysUserDO userDO = UserDetailsUtil.getUserDO();
        model.addAttribute("user", userDO);
        return "system/user/profile/avatar";
    }

    /**
     * 保存用户头像
     */
    @PostMapping("/updateAvatar")
    @ResponseBody
    public SkyResponse updateAvatar(@RequestParam("image") MultipartFile file) {
        //从数据库中获取最新的用户信息
        SysUserDO userDO = userService.getById(UserDetailsUtil.getUserId());
        try {
            if (!file.isEmpty()) {
                String contentType = file.getContentType();
                assert contentType != null;
                String extName = contentType.split("/")[1];
                //上传用户头像
                String url = AliyunUploadUtils.uploadFile(file.getBytes(), extName);
                userDO.setAvatar(url);
                userService.update(userDO);
                return SkyResponse.success();
            }
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "图像为空");
        } catch (Exception e) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "头像信息更新失败");
        }
    }
}


























