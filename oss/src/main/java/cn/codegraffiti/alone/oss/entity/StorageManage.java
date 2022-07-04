package cn.codegraffiti.alone.oss.entity;

import cn.codegraffiti.alone.core.enums.StateEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class StorageManage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String hash;

    private Integer property;

    private StateEnum state;

    private LocalDateTime createTime;

}
