package cn.codegraffiti.alone.finance.service;

import cn.codegraffiti.alone.common.R;
import cn.codegraffiti.alone.common.enums.StateEnum;
import cn.codegraffiti.alone.finance.entity.Flow;
import cn.codegraffiti.alone.finance.query.FlowQuery;
import cn.codegraffiti.alone.finance.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowService {

    final FlowRepository flowRepository;

    public R<Void> tally(Flow flow) {
        flow.setState(StateEnum.DEF);
        flow.setCreateTime(LocalDateTime.now());
        this.flowRepository.save(flow);
        return R.ok();
    }

    public R<List<Flow>> list(FlowQuery query) {
        Flow flow = new Flow();

        if (!ObjectUtils.isEmpty(query.getUser())) {
            flow.setUser(query.getUser());
        }

        if (!ObjectUtils.isEmpty(query.getType())) {
            flow.setType(query.getType());
        }

        Example<Flow> example = Example.of(flow);
        List<Flow> list = this.flowRepository.findAll(example);
        return R.ok(list);
    }
}
