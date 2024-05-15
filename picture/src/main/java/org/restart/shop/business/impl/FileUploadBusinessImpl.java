package org.restart.shop.business.impl;

import jakarta.annotation.Resource;
import org.restart.shop.business.FileUploadBusiness;
import org.restart.shop.domain.entities.UploadFileEntity;
import org.restart.shop.domain.query.UploadFileQuery;
import org.restart.shop.mapper.FileUploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploadBusinessImpl extends BaseBusinessImpl<UploadFileEntity> implements FileUploadBusiness {
    @SuppressWarnings("unused")
    private FileUploadMapper fileUploadMapper;

    @Autowired
    public void setBaseMapper(FileUploadMapper mapper) {
        super.setBaseMapper(mapper);
        this.fileUploadMapper = mapper;
    }
}
