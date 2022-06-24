package cn.codegraffiti.alone.oss.domain;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 存储对象
 */
@Data
public class StorageObject implements Serializable {

    private String originalName;

    private String name;

    private String hash;

    private Integer property;

    private InputStream inputStream;

    private long size;
}
