package com.springboot.commons;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.springboot.config.ConstantOSSConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class AliyunOSSUtil {

    private static final Logger LOGGER = Logger.getLogger(AliyunOSSUtil.class);

    private ConstantOSSConfig constantOSSConfig;

    /** 上传文件*/
    public String upLoad(File file){
        LOGGER.info("------OSS文件上传开始--------"+file.getName());
        String endpoint=constantOSSConfig.getOSS_END_POINT();
        System.out.println("获取到的Point为:"+endpoint);
        String accessKeyId=constantOSSConfig.getOSS_ACCESS_KEY_ID();
        String accessKeySecret=constantOSSConfig.getOSS_ACCESS_KEY_SECRET();
        String bucketName=constantOSSConfig.getOSS_BUCKET_NAME();
        String fileHost=constantOSSConfig.getOSS_FILE_HOST();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(new Date());

        // 判断文件
        if(file==null){
            return null;
        }
        OSSClient client=new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                LOGGER.info("------OSS文件上传成功------" + fileUrl);
            }
        }catch (OSSException oe){
            LOGGER.error(oe.getMessage());
        }catch (ClientException ce){
            LOGGER.error(ce.getErrorMessage());
        }finally{
            if(client!=null){
                client.shutdown();
            }
        }
        return null;
    }

}
