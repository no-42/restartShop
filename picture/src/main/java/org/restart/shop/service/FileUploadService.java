package org.restart.shop.service;

import jakarta.servlet.http.HttpServletRequest;
import org.restart.shop.domain.resp.UploadFileResp;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    UploadFileResp upload(MultipartFile file, HttpServletRequest request);
}
