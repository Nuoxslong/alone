package cn.codegraffiti.alone.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUser {
    private SecurityUser() {
    }

    public static String userInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (Objects.isNull(context)) {
            return null;
        }
        Authentication authentication = context.getAuthentication();
        if (Objects.isNull(authentication)) {
            return null;
        }
        return authentication.getName();
    }

}
