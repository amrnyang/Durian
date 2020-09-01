package com.swing.sky.oss.api.common;

import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.utils.aliyun.oss.AliyunUploadUtils;
import com.swing.sky.common.utils.security.TokenUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.framework.jwt.service.JwtService;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.oss.module.service.DurianUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author JIANG
 * @since 2020-09-01
 */
@RestController
@RequestMapping("profile")
public class ProfileController {
    @Resource
    private JwtService jwtService;

    private DurianUserService userService;

    @Autowired
    public void setUserService(DurianUserService userService) {
        this.userService = userService;
    }

    /**
     * 根据携带的jwt获取redis中的用户信息
     */
    @GetMapping("/get-info")
    public SkyResponse getInfo(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 检验token是否无效、是否过期，无效和过期都抛出异常，并从redis取出用户
            DurianUserDO user = jwtService.getLoginUser(token);
            if (user != null) {
                // 刷新token和redis中的过期时间，老token只要没过期依旧可以请求数据
                String newToken = jwtService.refreshToken(token);
                return SkyResponse.success(2)
                        .put("user", user)
                        .put("token", newToken);
            }
        }
        // 请求头中无token或redis无用户（已登出）
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }

    /**
     * 更新用户基本信息
     */
    @PostMapping("/update")
    public SkyResponse updateProfile(@RequestBody DurianUserDO user, HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 检验token是否无效、是否过期，无效和过期都抛出异常，并从redis取出用户
            DurianUserDO oldUser = jwtService.getLoginUser(token);
            if (oldUser != null) {
                // 生成一个要更新的对象
                DurianUserDO newUser = new DurianUserDO();
                newUser.setId(oldUser.getId());
                // 更改基本信息
                newUser.setNickName(user.getNickName());
                newUser.setPhone(user.getPhone());
                newUser.setAddress(user.getAddress());
                newUser.setEmail(user.getEmail());
                newUser.setGender(user.getGender());
                // 保存至数据库
                userService.update(newUser);

                // 更新redis中的用户信息，返回最新的token
                DurianUserDO updateUser = userService.getById(oldUser.getId());
                String newToken = jwtService.refreshUser(token, updateUser);
                return SkyResponse.success("基本信息更新成功！", 1)
                        .put("token", newToken);
            }
        }
        // 请求头中无token或redis无用户（已登出）
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }

    /**
     * 修改密码
     */
    @PostMapping("/resetPwd")
    public SkyResponse resetPassword(String oldPassword, String newPassword, HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 检验token是否无效、是否过期，无效和过期都抛出异常，并取出用户名(不经过redis)
            String username = jwtService.getUsername(token);
            // 刷新token和redis中的过期时间，老token只要没过期依旧可以请求数据
            String newToken = jwtService.refreshToken(token);

            //从数据库中获取最新的用户信息
            DurianUserDO user = userService.getUserByUsername(username);
            //校验原密码是否正确
            String password = user.getPassword();
            if (!new BCryptPasswordEncoder().matches(oldPassword, password)) {
                throw new RuntimeException("原密码不正确，请重新输入");
            }
            //密码加密保存
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userService.update(user);

            return SkyResponse.success("密码修改成功！", 1)
                    .put("token", newToken);
        }
        // 请求头中无token或redis无用户（已登出）
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }

    /**
     * 保存用户头像
     */
    @PostMapping("/updateAvatar")
    public SkyResponse updateAvatar(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 检验token是否无效、是否过期，无效和过期都抛出异常，并取出用户名(不经过redis)
            String username = jwtService.getUsername(token);
            // 刷新token和redis中的过期时间，老token只要没过期依旧可以请求数据
            String newToken = jwtService.refreshToken(token);

            //从数据库中获取最新的用户信息
            DurianUserDO user = userService.getUserByUsername(username);
            try {
                if (!file.isEmpty()) {
                    String contentType = file.getContentType();
                    assert contentType != null;
                    String extName = contentType.split("/")[1];
                    //上传用户头像
                    String url = AliyunUploadUtils.uploadFile(file.getBytes(), extName);
                    user.setAvatar(url);
                    userService.update(user);

                    return SkyResponse.success("头像保存成功！", 1)
                            .put("token", newToken);
                }
                return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "图像为空");
            } catch (Exception e) {
                return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "头像信息更新失败");
            }
        }
        // 请求头中无token或redis无用户（已登出）
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }
}
