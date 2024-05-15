package org.restart.shop.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 图片操作配置类
 */
@Configuration
//用于自动获取配置文件中storage.image中的字段
@ConfigurationProperties("storage.image")
@Setter
@Getter
public class ImageConfig {
    private String savePath;
    private List<String> allowType;

    @Value("server.servlet.context-path")
    private String contextPath;
}

