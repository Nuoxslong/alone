package cn.codegraffiti.alone.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     */
    @ResponseBody
    @ExceptionHandler(value = AloneException.class)
    public R<Void> businessExceptionHandler(AloneException e) {

        return R.fail(e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R<Void> exceptionHandler(Exception e) {
        return R.fail(e.getMessage());
    }
}