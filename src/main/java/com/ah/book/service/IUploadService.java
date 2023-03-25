package com.ah.book.service;

import java.io.InputStream;

public interface IUploadService {
    String uploadFileAvatar(InputStream inputStream, String originalFilename, String category);
}
