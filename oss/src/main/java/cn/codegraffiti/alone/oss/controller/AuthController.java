package cn.codegraffiti.alone.oss.controller;

import cn.codegraffiti.alone.core.R;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping
    public R<Void> auth(@RequestParam String code) {
        HttpRequest request = HttpUtil.createPost("localhost:5000/oauth2/token");
        request.basicAuth("alone_oss", "oss_admin");
        request.form(Map.of("code", code, "grant_type", "authorization_code", "redirect_uri", "http://127.0.0.1:6000/auth"));
        HttpResponse response = request.execute();
        System.out.println(response.body());
        return R.ok();
    }
}
