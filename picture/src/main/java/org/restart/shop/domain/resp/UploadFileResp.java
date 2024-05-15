package org.restart.shop.domain.resp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadFileResp {
    private String md5;
    private String url;
    private String localPath;
}
