package cn.codegraffiti.alone.finance.config;

import cn.codegraffiti.alone.finance.interceptor.SignatureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private SignatureInterceptor signatureInterceptor;

    @Autowired
    public void setSignatureInterceptor(SignatureInterceptor signatureInterceptor) {
        this.signatureInterceptor = signatureInterceptor;
    }

    /**
     * 扩展拦截器
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        // 注册拦截器
        registry.addInterceptor(signatureInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/re_test/**");
    }
}
