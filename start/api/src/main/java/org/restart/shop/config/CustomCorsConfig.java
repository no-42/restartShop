package org.restart.shop.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomCorsConfig {

    /**
     * 图片保存路径，自动从yml文件中获取数据
     * 示例： E:/images/
     */
    @Resource
    ImageConfig imageConfig;//一开始编写的文件配置类

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // 允许的来源域名，* 表示允许所有域名
                        .allowedMethods("POST") // 允许的请求方法
//                        .allowedHeaders("Content-Type", "X-Requested-With") // 允许的请求头
                        .maxAge(3600); // 允许的缓存时间，单位为秒
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                /**
                 * 配置资源映射
                 * 意思是：如果访问的资源路径是以“/images”开头的，
                 * 就给我映射到本机的“E:/upload/”这个文件夹内，去找你要的资源
                 * 注意：E:/upload/ 后面的 “/”一定要带上
                 */
                registry.addResourceHandler("/images/**").addResourceLocations("file:" + imageConfig.getSavePath());
            }
        };
    }
}
