package cn.codegraffiti.alone.finance.controller;

import cn.codegraffiti.alone.core.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/re_test")
public class ReTestController {

    @GetMapping(value = "/json_serial")
    public R<String> jsonSerial() {
        log.info("currentTimeMillis:{}", System.currentTimeMillis());
        return R.ok("re_test");
    }

}
