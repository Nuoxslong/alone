package cn.codegraffiti.alone.security.endpoint;

import cn.codegraffiti.alone.common.R;
import cn.codegraffiti.alone.common.utils.HttpUtil;
import cn.codegraffiti.alone.common.utils.JsonUtil;
import cn.codegraffiti.alone.security.domain.AloneOauthToken;
import cn.codegraffiti.alone.security.properties.ComponentSecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthEndpoint {

    final ComponentSecurityProperties properties;

    @GetMapping(value = "/access_token")
    public R<AloneOauthToken> callback(@RequestParam String code) {
        String authDomain = properties.getAuthDomain();
        String intranetDomain = this.properties.getIntranetDomain();
        String responseBody = HttpUtil.post_form(
                authDomain + "/oauth2/token",
                Map.of("code", code,
                        "grant_type", "authorization_code",
                        "redirect_uri", intranetDomain + "/oauth2/access_token"),
                HttpUtil.basicAuth(properties.getClientId(), properties.getClientSecret()));
        return R.ok(JsonUtil.toBean(responseBody, AloneOauthToken.class));
    }

    @GetMapping(value = "/refresh_token")
    public R<AloneOauthToken> refreshToken(@RequestParam String token) {
        String authDomain = properties.getAuthDomain();
        String responseBody = HttpUtil.post_form(authDomain + "/oauth2/token",
                Map.of("refresh_token", token, "grant_type", "refresh_token"),
                HttpUtil.basicAuth(properties.getClientId(), properties.getClientSecret()));
        return R.ok(JsonUtil.toBean(responseBody, AloneOauthToken.class));
    }
}
