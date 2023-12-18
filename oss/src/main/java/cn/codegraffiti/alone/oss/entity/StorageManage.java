package cn.codegraffiti.alone.oss.entity;

import cn.codegraffiti.alone.common.enums.StateEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

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
