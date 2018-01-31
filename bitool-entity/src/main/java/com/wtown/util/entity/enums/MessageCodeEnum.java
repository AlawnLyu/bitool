/**
 * @author LYU
 * @create 2018-01-12-15:31
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.entity.enums;

public enum MessageCodeEnum implements MessageCode {
    SUCCESS(1000, "ok"),
    PARAMS_VALIDATOR_FAIL(2000, "参数错误"),
    PARAMS_FORMAT_FAIL(2001, "数据格式错误"),

    DOWNLOAD_ERROR(5000, "下载错误"),
    FILE_NOT_EXISTS(5001, "文件不存在");

    private int code;
    private String message;

    MessageCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code + "";
    }

    @Override
    public String getMessageDesc() {
        return message;
    }
}
