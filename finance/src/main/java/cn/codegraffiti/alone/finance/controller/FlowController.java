package cn.codegraffiti.alone.finance.controller;

import cn.codegraffiti.alone.common.R;
import cn.codegraffiti.alone.finance.entity.Flow;
import cn.codegraffiti.alone.finance.query.FlowQuery;
import cn.codegraffiti.alone.finance.service.FlowService;
import cn.codegraffiti.alone.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/flow")
public class FlowController {

    final FlowService flowService;

    @PostMapping
    public R<Void> tally(@RequestBody Flow flow) {
        flow.setUser(SecurityUser.principal());
        return this.flowService.tally(flow);
    }

    @GetMapping(value = "/list")
    public R<List<Flow>> list (FlowQuery query) {
        return this.flowService.list(query);
    }

}
