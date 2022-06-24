package cn.codegraffiti.alone.oss.enums.storage;

import lombok.Getter;

@Getter
public enum PropertyEnum {

    TEMP(1, "临时存储"),

    ATTACHMENT(100, "关联附件");

    final Integer code;

    final String desc;

    PropertyEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
