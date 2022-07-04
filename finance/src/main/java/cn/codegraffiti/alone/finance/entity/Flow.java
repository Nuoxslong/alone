package cn.codegraffiti.alone.finance.entity;

import cn.codegraffiti.alone.core.enums.StateEnum;
import cn.codegraffiti.alone.finance.enums.FlowType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Flow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private FlowType type;

    private BigDecimal amount;

    private String remark;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    private LocalDateTime createTime;

}
