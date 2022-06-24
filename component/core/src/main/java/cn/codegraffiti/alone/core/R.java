package cn.codegraffiti.alone.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;


    public static <T> R<T> ok() {
        return R.ok(null);
    }

    public static <T> R<T> ok(T data) {
        return R.ok("", data);
    }

    public static <T> R<T> ok(String message, T data) {
        return new R<>(200, message, data);
    }


    public static <T> R<T> fail() {
        return R.fail("", null);
    }

    public static <T> R<T> fail(String message) {
        return R.fail(message, null);
    }

    public static <T> R<T> fail(String message, T data) {
        return new R<>(500, message, data);
    }

    public R() {
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * 判断返回值对象是否成功
     * true or false
     */
    public boolean success() {
        return this.code == 200;
    }

    /**
     * @param result  the 返回结果
     * @param message the 失败信息
     *                根据result返回不同状态码
     *                result == true , 返回R.ok();
     *                result == false, 返回R.fail(message);
     */
    public static <T> R<T> assertion(boolean result, String message) {
        if (result) {
            return R.ok();
        }
        return R.fail(message);
    }
}
