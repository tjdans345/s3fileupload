package com.meteor.s3fileupload.enumpackge;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    FILE_SAVE(0, "파일 저장에 성공하였습니다."),
    FILE_SAVE_FAIL(1, "파일 저장에 실패하였습니다. 관리자에게 문의해주세요.");

    private final Integer code;
    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

}