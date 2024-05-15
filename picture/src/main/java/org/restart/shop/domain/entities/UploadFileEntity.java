package org.restart.shop.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.restart.shop.core.doamin.IdEntity;

import javax.persistence.Column;

@Setter
@Getter
@TableName(value = "upload_file", schema = "file")
public class UploadFileEntity extends IdEntity {
    /**
     * 图片地址
     */
    @Column(name = "local_path")
    private String localPath;
    /**
     * 图片名称
     */
    @Column(name = "file_md5")
    private String fileMd5;
    /**
     * 图片服务器地址
     */
    @Column(name = "url")
    private String url;
}
