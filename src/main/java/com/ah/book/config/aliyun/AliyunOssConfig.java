package com.ah.book.config.aliyun;

import com.ah.book.common.utils.ConstantPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@Accessors(chain = true)
public class AliyunOssConfig {

    /**
     * 将OSSClient放入spring上下文中
     * @return
     */
    @Bean
    public OSS OSSClient(){
        return new OSSClient(ConstantPropertiesUtils.END_POINT,ConstantPropertiesUtils.ACCESS_KEY_ID,ConstantPropertiesUtils.ACCESS_KEY_SECRET);
    }
}
