package cn.codegraffiti.alone.core.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class JsonUtil {

    static ObjectMapper objectMapper = null;

    private JsonUtil() {
    }

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 需要转换为 JSON 字符串的对象
     * @return 转换后的 JSON 字符串
     * @throws RuntimeException 如果 JSON 解析出错，将抛出 RuntimeException 异常
     */
    public static String toJsonString(Object obj) {
        return handleJsonProcessingException(() -> objectMapper.writeValueAsString(obj));
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json  JSON 字符串
     * @param clazz 目标对象的类型
     * @param <T>   目标对象的类型参数
     * @return 转换后的对象
     * @throws RuntimeException 如果 JSON 解析出错，将抛出 RuntimeException 异常
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        lazyLoad();
        return handleJsonProcessingException(() -> objectMapper.readValue(json, clazz));
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json          JSON 字符串
     * @param typeReference 目标对象的 TypeReference
     * @param <T>           目标对象的类型参数
     * @return 转换后的对象
     * @throws RuntimeException 如果 JSON 解析出错，将抛出 RuntimeException 异常
     */
    public static <T> T toBean(String json, TypeReference<T> typeReference) {
        lazyLoad();
        return handleJsonProcessingException(() -> objectMapper.readValue(json, typeReference));

    }

    private static void lazyLoad() {
        if (Objects.isNull(objectMapper)) {
            objectMapper = SpringUtil.getBean(ObjectMapper.class);
        }
    }

    /**
     * 处理 JSON 处理异常的方法
     *
     * @param function 处理 JSON 处理异常的函数接口
     * @param <R>      返回值的类型参数
     * @return 处理结果
     * @throws RuntimeException 如果 JSON 解析出错，将抛出 RuntimeException 异常
     */
    private static <R> R handleJsonProcessingException(JsonProcessingFunction<R> function) {
        try {
            return function.apply();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json Processing Exception", e);
        }
    }

    /**
     * 定义处理 JSON 处理异常的函数接口
     *
     * @param <R> 返回值的类型参数
     */
    private interface JsonProcessingFunction<R> {
        R apply() throws JsonProcessingException;
    }

}
