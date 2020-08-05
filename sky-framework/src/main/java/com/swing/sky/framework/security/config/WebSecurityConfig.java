package com.swing.sky.framework.security.config;

import com.swing.sky.framework.security.filter.CaptchaFilter;
import com.swing.sky.framework.security.handler.AuthenticationEntryPointImpl;
import com.swing.sky.framework.security.handler.LoginFailureHandler;
import com.swing.sky.framework.security.handler.LoginSuccessHandler;
import com.swing.sky.framework.security.handler.LogoutSuccessHandlerImpl;
import com.swing.sky.framework.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author swing
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自定义用户认证
     */
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 认证过滤器
     */
    @Resource
    private CaptchaFilter captchaFilter;

    /**
     * 登陆成功处理类
     */
    @Resource
    private LoginSuccessHandler loginSuccessHandler;


    /**
     * 登录失败处理类
     */
    @Resource
    private LoginFailureHandler loginFailureHandler;

    /**
     * 认证失败处理类
     */
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * 数据源
     */
    @Resource
    private DataSource dynamicDataSource;

    /**
     * 退出处理类
     */
    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * 指定身份认证接口和秘密啊加密方式
     *
     * @param auth 身份验证管理器生成器
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用csrf （跨站请求伪造）
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // 过滤请求
                .authorizeRequests()
                // 对于登录login 验证码captchaImage 允许匿名访问
                .antMatchers("/login", "/captcha", "/test", "/test2").permitAll()
                //静态资源的访问无需认证
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.ico",
                        "/**/*.map",
                        "/**/*.woff",
                        "/**/*.woff2",
                        "/**/*.ttf"
                ).permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                //验证码过滤器设置在UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                //认证失败处理类
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //登出成功处理类（如果有记住我的功能，这里会自动删除cookie中的remember-me
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and()
                //配置表单登录
                .formLogin()
                //指定登录页面
                .loginPage("/login")
                //指定登录处理程序
                .loginProcessingUrl("/login")
                //指定登录成功处理类
                .successHandler(loginSuccessHandler)
                //指定登录失败处理类
                .failureHandler(loginFailureHandler)
                //由后端控制重定向（本项目是由前端根据JSON结果来重定向）
//                .defaultSuccessUrl("/index")
//                .failureUrl("/login")
                .and()
                //记住我的配置
                .rememberMe()
                //一直开启记住我的功能
//                .alwaysRemember(true)
                .rememberMeCookieName("sky-remember-me")
                //配置令牌的存储方式（存储在数据库中）
                .tokenRepository(persistentTokenRepository())
                //记住我的有效期
                .tokenValiditySeconds(60 * 30)
                .userDetailsService(userDetailsService);
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return AuthenticationManager
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置记住我的用户信息，并在数据库中持久化
     * 在第一个启动程序的时候，可以加上如下语句，将自动创建官方默认结构的表，然后将该句注释掉
     * jdbcTokenRepository.setCreateTableOnStartup(true);
     *
     * @return 结果
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dynamicDataSource);
        return jdbcTokenRepository;
    }
}