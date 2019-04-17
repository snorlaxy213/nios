package com.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ConstantOSSConfig {
    @Value("${oss.endpoint}")
    private   String OSS_END_POINT;
    @Value("${oss.keyid}")
    private  String OSS_ACCESS_KEY_ID;
    @Value("${oss.keysecret}")
    private  String OSS_ACCESS_KEY_SECRET;
    @Value("${oss.filehost}")
    private  String OSS_FILE_HOST;
    @Value("${oss.bucketname}")
    private  String OSS_BUCKET_NAME;

    public String getOSS_END_POINT() {
        return OSS_END_POINT;
    }

    public void setOSS_END_POINT(String OSS_END_POINT) {
        this.OSS_END_POINT = OSS_END_POINT;
    }

    public String getOSS_ACCESS_KEY_ID() {
        return OSS_ACCESS_KEY_ID;
    }

    public void setOSS_ACCESS_KEY_ID(String OSS_ACCESS_KEY_ID) {
        this.OSS_ACCESS_KEY_ID = OSS_ACCESS_KEY_ID;
    }

    public String getOSS_ACCESS_KEY_SECRET() {
        return OSS_ACCESS_KEY_SECRET;
    }

    public void setOSS_ACCESS_KEY_SECRET(String OSS_ACCESS_KEY_SECRET) {
        this.OSS_ACCESS_KEY_SECRET = OSS_ACCESS_KEY_SECRET;
    }

    public String getOSS_FILE_HOST() {
        return OSS_FILE_HOST;
    }

    public void setOSS_FILE_HOST(String OSS_FILE_HOST) {
        this.OSS_FILE_HOST = OSS_FILE_HOST;
    }

    public String getOSS_BUCKET_NAME() {
        return OSS_BUCKET_NAME;
    }

    public void setOSS_BUCKET_NAME(String OSS_BUCKET_NAME) {
        this.OSS_BUCKET_NAME = OSS_BUCKET_NAME;
    }
}
