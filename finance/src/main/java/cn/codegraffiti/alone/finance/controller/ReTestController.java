package cn.codegraffiti.alone.finance.controller;

import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.core.utils.JsonUtil;
import cn.codegraffiti.alone.finance.entity.Flow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/re_test")
public class ReTestController {

    @GetMapping(value = "/json_serial")
    public R<Long> jsonSerial() {
        String str = """
                {
                    "id":"1"
                }
                """;
        Flow flow = JsonUtil.toBean(str, Flow.class);
        return R.ok(flow.getId());
    }

}
