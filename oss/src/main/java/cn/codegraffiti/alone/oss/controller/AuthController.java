package cn.codegraffiti.alone.oss.controller;

import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.security.domain.AloneOauthToken;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    final ObjectMapper objectMapper;

    @GetMapping
    public R<AloneOauthToken> callback(@RequestParam String code) throws JsonProcessingException {
        HttpRequest request = HttpUtil.createPost("http://127.0.0.1:9999/oauth2/token");
        request.basicAuth("alone_oss", "oss_admin");
        request.form(Map.of("code", code, "grant_type", "authorization_code", "redirect_uri", "http://127.0.0.1:10010/auth"));
        HttpResponse response = request.execute();
        return R.ok(this.objectMapper.readValue(response.body(), AloneOauthToken.class));
    }
}
