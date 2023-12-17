package cn.codegraffiti.alone.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 业务属性配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class ComponentSecurityProperties {


    /**
     * 认证服务地址
     */
    private String authDomain = "http://127.0.0.1:9999";

    /**
     * 当前服务访问地址（外网）
     */
    private String externallyDomain = "http://127.0.0.1:9999";

    /**
     * 当前服务访问地址（内网）
     */
    private String intranetDomain = "http://127.0.0.1:9999";

    /**
     * oauth2授权id
     */
    private String clientId = "alone";

    /**
     * oauth2授权secret
     */
    private String clientSecret = "admin";

}
