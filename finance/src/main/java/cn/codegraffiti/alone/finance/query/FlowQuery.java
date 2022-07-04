package cn.codegraffiti.alone.finance.query;

import cn.codegraffiti.alone.finance.enums.FlowType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FlowQuery implements Serializable {

    /**
     * 查询用户
     */
    private String user;

    /**
     * 流水类型
     */
    private FlowType type;

    /**
     * 搜索时间范围（开始时间）
     */
    private LocalDateTime startTime;

    /**
     * 搜索时间范围（结束时间）
     */
    private LocalDateTime endTime;

}