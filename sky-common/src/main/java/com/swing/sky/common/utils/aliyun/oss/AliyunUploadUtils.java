package com.swing.sky.common.utils.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.swing.sky.common.utils.UUIDUtils;

import java.io.ByteArrayInputStream;

/**
 * 阿里云文件上传工具
 *
 * @author swing
 */
public class AliyunUploadUtils {
    /**
     * 上传文件至阿里云
     *
     * @param bytes   文件字节数组
     * @param extName 文件扩展名
     * @return 文件下载路径
     */
    public static String uploadFile(byte[] bytes, String extName) {
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4GCsZtSL5d52af57pDDb";
        String accessKeySecret = "396h5gsL5Drg61zZLfuZbf9LB0CuEM";
        String bucketName = "swing-durian";
        String objectName = getObjectName(extName);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://swing-sky.oss-cn-beijing.aliyuncs.com/" + objectName;
    }


    /**
     * 获取文件的唯一ObjectName
     * 例子 2a/c4/2ac4c4293334405b9d9ec89611021e22.jpg
     *
     * @param extName 文件扩展名
     * @return 唯一ObjectName
     */
    public static String getObjectName(String extName) {
        String fileName = UUIDUtils.getUuid();
        //取前两位作为一级目录，三四位作为二级目录
        String dir1 = fileName.substring(0, 2);
        String dir2 = fileName.substring(2, 4);
        return dir1 + "/" + dir2 + "/" + fileName + "." + extName;
    }
}
