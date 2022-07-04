package cn.codegraffiti.alone.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 设置加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 将密码加密方式采用委托方式，默认以BCryptPasswordEncoder方式进行加密，兼容ldap,MD4,MD5等方式
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 针对http请求，进行拦截过滤
     * <p>
     * CookieCsrfTokenRepository进行CSRF保护的工作方式：
     * 1.客户端向服务器发出GET请求，例如请求主页
     * 2.Spring发送 GET 请求的响应以及 Set-cookie 标头，其中包含安全生成的XSRF令牌
     */
    @Bean
    public SecurityFilterChain loginSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.antMatchers("/login", "/oauth2/**").permitAll()
                                .anyRequest().authenticated()
                )
                // 使用默认登录页面
                .formLogin(withDefaults())
                // 关闭 Spring Security CSRF保护
                .csrf().disable()
        // Spring Security CSRF保护
        // .csrf(csrfToken -> csrfToken.csrfTokenRepository(new CookieCsrfTokenRepository()))
        ;
        return httpSecurity.build();
    }

}