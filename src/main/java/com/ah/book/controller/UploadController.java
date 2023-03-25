package com.ah.book.controller;

import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.exception.ServiceException;
import com.ah.book.service.IUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

@RestController
@RequestMapping("/system/upload")
public class UploadController {

    @Resource
    IUploadService uploadService;
    @PostMapping("/img")
    public AjaxResult uploadFile(@RequestBody MultipartFile file){
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e){
            throw new ServiceException("上传失败");
        }
        String fileName = uploadService.uploadFileAvatar(inputStream, file.getOriginalFilename(), "articles");
        return AjaxResult.success(fileName);
    }
}
