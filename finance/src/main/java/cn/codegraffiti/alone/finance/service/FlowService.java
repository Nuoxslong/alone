package cn.codegraffiti.alone.finance.service;

import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.core.enums.StateEnum;
import cn.codegraffiti.alone.finance.entity.Flow;
import cn.codegraffiti.alone.finance.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
