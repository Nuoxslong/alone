package cn.codegraffiti.alone.finance.interceptor;

import cn.codegraffiti.alone.core.AloneException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Component
public class SignatureInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 拦截所有为带有指定请求头的请求
        String signature = request.getHeader("signature");

        if (ObjectUtils.isEmpty(signature)) {
            throw new AloneException("Unknown anomaly");
        }

        if (!"EFB5803993A803FE4414FA2CA001A84F".equals(signature)) {
            throw new AloneException("Go away");
        }

        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }
}
