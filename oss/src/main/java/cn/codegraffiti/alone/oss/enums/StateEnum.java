package cn.codegraffiti.alone.oss.enums;

import lombok.Getter;

@Getter
public enum StateEnum {

    DEF(1, "默认");

    final Integer code;

    final String desc;

    StateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
