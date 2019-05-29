package com.fei.springboothigh.enums;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/5/27
 * @Description: 性别枚举
 */
public enum SexEnum {
    /**
     * 男
     */
    MALE(0, "男"),
    /**
     * 女
     */
    FAMALE(1, "女"),
    /**
     * 未知
     */
    UNKOOW(2, "未知");

    @Getter
    private Integer sexCode;
    @Getter
    private String sexDesc;

    SexEnum(Integer sexCode, String sexDesc) {
        this.sexCode = sexCode;
        this.sexDesc = sexDesc;
    }

    /**
     * 根据枚举code获得枚举
     * @param code
     * @return SexEnum
     */
    public SexEnum getByCode(Integer code) {
        SexEnum sexEnum = null;
        // values()方法获得所有枚举类
        SexEnum[] sexEnums = SexEnum.values();
        for (SexEnum anEnum : sexEnums) {
            if (anEnum.getSexCode().equals(code)) {
                sexEnum = anEnum;
                break;
            }
        }
        return sexEnum;
    }
}
