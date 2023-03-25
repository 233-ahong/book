package com.ah.book.service.impl;

import com.ah.book.common.utils.ConstantPropertiesUtils;
import com.ah.book.service.IUploadService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.util.UUID;

@Service("uploadService")
public class UploadServiceImpl implements IUploadService {
    @Override
    public String uploadFileAvatar(InputStream inputStream, String originalFilename, String category) {
        //工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtils.FILE_HOST;

        //创建OSS实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String folder = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // oss中的文件夹名
        String objectName = fileHost + "/" + category + "/" + folder + "/" + fileName + fileExtension;

        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header(设置了才能通过返回的链接直接访问)。
        //如果不设置，直接访问url会自行下载图片，看各位自己选择
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");

        ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }
}
