package org.rails.domain;

import org.rails.data.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadResponse uploadFile(MultipartFile multipartFile);
}