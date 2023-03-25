package com.ah.book.common.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    //读取配置内容
    @Value("${aliyun.endPoint}")
    private String endPoint;

    @Value("${aliyun.keyId}")
    private String keyId;

    @Value("${aliyun.keySecret}")
    private String keySecret;

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Value("${aliyun.fileHost}")
    private String fileHost;

    private String test;


    //定义公开的静态常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
        FILE_HOST = fileHost;
    }
}

