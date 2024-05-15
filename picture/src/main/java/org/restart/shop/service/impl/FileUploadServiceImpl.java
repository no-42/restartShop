package org.restart.shop.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.restart.shop.business.FileUploadBusiness;
import org.restart.shop.config.ImageConfig;
import org.restart.shop.domain.entities.UploadFileEntity;
import org.restart.shop.domain.query.UploadFileQuery;
import org.restart.shop.domain.resp.UploadFileResp;
import org.restart.shop.exception.RsException;
import org.restart.shop.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Resource
    private FileUploadBusiness fileUploadBusiness;

    @Resource
    ImageConfig imageConfig;//一开始编写的文件配置类

    @Override
    public UploadFileResp upload(MultipartFile uploadFile, HttpServletRequest request) {
        UploadFileResp resp = new UploadFileResp();
        //获取文件类型，根据文件后缀判断文件类型的方式不安全！
        String contentType = uploadFile.getContentType();
        String type = contentType.substring(contentType.indexOf("/") + 1);

        //文件操作返回结果
        if (!imageConfig.getAllowType().contains(type)) {
            //判断是否为允许的文件类型
            throw new RsException("-1", "保存失败，仅支持：" + imageConfig.getAllowType());
        }
        //图片字节数组的md5
        String md5 = null;
        try {
            md5 = SecureUtil.md5(uploadFile.getInputStream());

            UploadFileQuery query = new UploadFileQuery();
            query.setFileMd5(md5);
            UploadFileEntity entity = fileUploadBusiness.selectOneByQuery(query);
            if (entity != null) {
                resp.setMd5(entity.getFileMd5());
                resp.setUrl(entity.getUrl());
                resp.setLocalPath(entity.getLocalPath());
                return resp;
            }
            //图片保存路径,每天的图片分开保存
            String path = imageConfig.getSavePath();
            String directory = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
            File file = new File(path + directory);
            if (!file.exists()) {
                //创建文件夹,会自动创建父文件夹
                file.mkdirs();
            }
            String newFilePath = path + directory + md5 + "." + type;
            File newFile = new File(newFilePath);
            uploadFile.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api_v3/images/" + directory + md5 + "." + type;
            UploadFileEntity uploadFileEntity = new UploadFileEntity();
            uploadFileEntity.setFileMd5(md5);
            uploadFileEntity.setLocalPath(newFilePath);
            uploadFileEntity.setUrl(url);
            fileUploadBusiness.insert(uploadFileEntity);
            resp.setUrl(url);
            resp.setMd5(md5);
            resp.setLocalPath(newFilePath);
            return resp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
