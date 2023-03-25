package com.ah.book.service.impl;

import com.ah.book.common.constant.Constants;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.redis.RedisCache;
import com.ah.book.common.utils.HttpUtilsS;
import com.ah.book.common.utils.uuid.IdUtils;
import com.ah.book.service.ICodeService;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CodeServiceImpl implements ICodeService {
    private String code;
    @Resource
    private RedisCache redisService;
    @Override
    public AjaxResult code(String phone) {
        final String uuid = getCode(phone);
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "b436627820534c44b45627ae1dc59b50";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", "TPL_0000");
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtilsS.doPost(host, path, method, headers, querys, bodys);
            // System.out.println(response.toString());
            //获取response的body
            // System.out.println(EntityUtils.toString(response.getEntity()));
            return AjaxResult.success(uuid);
        } catch (Exception e) {
            return AjaxResult.error("验证码发送失败");
        }
    }

    @Override
    public String getCode(String phone){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        code = builder.toString();
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid + phone;
        redisService.setCacheObject(verifyKey, builder.toString(), Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return uuid;
    }
}
