package cn.codegraffiti.alone.finance.controller;

import cn.codegraffiti.alone.core.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/re_test")
public class ReTestController {

    @GetMapping(value = "/json_serial")
    public R<String> jsonSerial() {
        return R.ok("re_test");
    }

}
