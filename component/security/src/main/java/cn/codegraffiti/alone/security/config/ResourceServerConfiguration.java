package cn.codegraffiti.alone.security.config;

import cn.codegraffiti.alone.security.AloneAccessDeniedHandler;
import cn.codegraffiti.alone.security.AloneAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // 开启鉴权服务
public class ResourceServerConfiguration {

    @Bean
    public SecurityFilterChain httpSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // 所有请求都进行拦截
        httpSecurity.authorizeHttpRequests(http ->
                http.requestMatchers("/re_test/**", "/error", "/favicon.ico", "/access_token").permitAll()
                        .anyRequest().authenticated());
        // 关闭session
        httpSecurity.sessionManagement(Customizer.withDefaults());
        // 配置资源服务器的无权限，无认证拦截器等 以及JWT验证
        httpSecurity.oauth2ResourceServer(authorizeRequests ->
                authorizeRequests
                        .accessDeniedHandler(new AloneAccessDeniedHandler())
                        .authenticationEntryPoint(new AloneAuthenticationEntryPoint())
                        .jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }

}