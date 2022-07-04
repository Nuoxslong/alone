package cn.codegraffiti.alone.finance.controller;

import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.finance.entity.Flow;
import cn.codegraffiti.alone.finance.service.FlowService;
import cn.codegraffiti.alone.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/flow")
public class FlowController {

    final FlowService flowService;

    @PostMapping
    public R<Void> tally(@RequestBody Flow flow) {
        String s = SecurityUser.userInfo();
        System.out.println(s);
        return this.flowService.tally(flow);
    }

}
