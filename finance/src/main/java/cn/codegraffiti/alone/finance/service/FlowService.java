package cn.codegraffiti.alone.finance.service;

import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.core.enums.StateEnum;
import cn.codegraffiti.alone.finance.entity.Flow;
import cn.codegraffiti.alone.finance.query.FlowQuery;
import cn.codegraffiti.alone.finance.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Slf4j
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
        List<Flow> list = this.flowRepository.findAll(example, Sort.sort(Flow.class).by(Flow::getCreateTime).descending());
        log.info("def TimeZone: {}", TimeZone.getDefault());
        log.info("time test: {}", list.get(0).getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        return R.ok(list);
    }

    public Flow last() {
        return this.flowRepository.last();
    }
}
