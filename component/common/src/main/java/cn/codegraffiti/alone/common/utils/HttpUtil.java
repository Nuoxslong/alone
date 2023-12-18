package cn.codegraffiti.alone.common.utils;

import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {

    private static final Long TIMEOUT = 60L;

    public static final OkHttpClient okHttpClient = okHttpClient();

    private HttpUtil() {
    }

    public static String post_json(String url, String requestBody) {
        return executePost(url, requestBody);
    }

    public static String post_form(String url, Map<String, String> params, Map<String, String> headers) {
        return executePost(url, params, headers);
    }

    public static Map<String, String> basicAuth(String id, String secret) {
        return Map.of("Authorization", "Basic " + BaseEncoding.base64().encode((id + ":" + secret).getBytes(StandardCharsets.UTF_8)));
    }

    public static String executePost(String url, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach(builder::add);
        return post(url, builder.build(), headers);
    }

    private static String executePost(String url, String data) {
        return post(url,
                RequestBody.create(data, MediaType.parse("application/json; charset=utf-8")),
                Map.of());
    }

    private static String post(String url, RequestBody requestBody, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (Objects.nonNull(headers) && !headers.isEmpty()) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.url(url).post(requestBody).build();
        log.info("http request url:{}", url);
        log.info("http request body:{}", requestBodyToString(requestBody));
        log.info("http request headers:{}", request.headers());
        return execute(request);
    }

    private static String execute(Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseBody = "";
            if (Objects.nonNull(response.body())) {
                responseBody = response.body().string();
            }
            log.info("http response status:{}", response.code());
            log.info("http response body:{}", responseBody);
            return responseBody;
        } catch (IOException e) {
            log.error("http request execute error!", e);
            throw new RuntimeException(e);
        }
    }

    public static String requestBodyToString(RequestBody requestBody) {
        Buffer buffer = new Buffer();
        try {
            requestBody.writeTo(buffer);
        } catch (IOException e) {
            log.error("http response body error", e);
            throw new RuntimeException(e);
        }
        return buffer.readUtf8();
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // 设置证书请求
                // .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                // 是否开启缓存
                .retryOnConnectionFailure(false)
                // 设置连接池
                // .connectionPool(pool())
                // 设置超时时间
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                // 设置代理
                // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                // 拦截器
                // .addInterceptor()
                .build();
    }

}
