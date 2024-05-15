package org.restart.shop.controller.upload;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.restart.shop.domain.resp.UploadFileResp;
import org.restart.shop.resp.ResultData;
import org.restart.shop.service.FileUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/upload")
public class PictureUploadCtrl {
    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/pic")
    @ResponseBody
    private String uploadPic(@RequestParam("file") MultipartFile filePath, HttpServletRequest request) {
        UploadFileResp resp = fileUploadService.upload(filePath, request);
        return ResultData.successToJson(resp);
    }
}
