package com.wms.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.Calendar;

/**
 * @Author wangyang
 * @description 文件处理
 * @Date 2019/11/11
 */
@Slf4j
@AllArgsConstructor
public class FileUtils {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String attachmentBucketName;
    private Integer defaultUrlMinTimeout;

    public Boolean uploadFile(String fileKey, File file) {
        if (fileKey.isEmpty() || !(file.isFile())) {
            throw new NullPointerException();
        }
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(attachmentBucketName)) {
            throw new NullPointerException();
        }
        try {
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(attachmentBucketName, fileKey, file));
            if (null != result) {
                log.info("文件上传成功");
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }
        return false;
    }

    public URL getFileUrlByFileKey(String fileKey, Integer urlMinTimeout) {
        if (urlMinTimeout == null) {
            urlMinTimeout = defaultUrlMinTimeout;
        }
        Calendar expiredTime = Calendar.getInstance();
        expiredTime.add(Calendar.MINUTE, urlMinTimeout);
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        URL url = null;
        try {
            url = ossClient.generatePresignedUrl(attachmentBucketName, fileKey, expiredTime.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }
        return url;
    }

    public Boolean deleteByFileKey(String fileKey) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(attachmentBucketName, fileKey);
            log.info("文件删除成功");
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }
    }

}