package com.swing.sky.system.api.common;

import com.swing.sky.common.utils.FileUtils;
import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.utils.aliyun.oss.AliyunUploadUtils;
import com.swing.sky.system.framework.SkyConfig;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.framework.web.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载处理
 *
 * @author swing
 */
@Controller
public class DownloadController {
    private static final Logger log = LoggerFactory.getLogger(DownloadController.class);

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = SkyConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求,将文件上传至阿里云，文件将url路径存储在数据库
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public SkyResponse uploadFile(MultipartFile file) throws Exception {
        try {
            if (!file.isEmpty()) {
                //上传文件到阿里云
                String contentType = file.getContentType();
                assert contentType != null;
                String extName = contentType.split("/")[1];
                String url = AliyunUploadUtils.uploadFile(file.getBytes(), extName);
                return SkyResponse.success(2)
                        .put("fileName", file.getOriginalFilename())
                        .put("url", url);
            }
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "图像为空");
        } catch (Exception e) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "图像上传失败");
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = SkyConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, "/profile");
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     *
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
