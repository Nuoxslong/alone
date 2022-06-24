package cn.codegraffiti.alone.oss.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioOssConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://oss.alone.codegraffiti.cn")
                .credentials("admin", "nuo_minio")
                .build();
    }

}
