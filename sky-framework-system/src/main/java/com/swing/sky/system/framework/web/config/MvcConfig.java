package com.swing.sky.system.framework.web.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springMVC配置
 *
 * @author swing
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
//    /**
//     * 自定义静态资源路径
//     * addResourceHandler：指的是对外暴露的访问路径
//     * addResourceLocations：指的是内部文件放置的目录
//     *
//     * @param registry 注册中心
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //页面静态资源路径
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCacheControl(CacheControl.empty());
//        // swagger配置
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}