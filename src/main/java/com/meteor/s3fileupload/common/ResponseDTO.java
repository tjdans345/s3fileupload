package com.meteor.s3fileupload.common;

import com.meteor.s3fileupload.enumpackge.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResponseDTO<T> {

    private final Integer code;
    private final String message;
    private final T content;

    public ResponseDTO(ResponseEnum responseEnum, T content) {
        this.code = responseEnum.getCode();
        this.message  = responseEnum.getMessage();
        this.content = content;
    }

    public ResponseDTO(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.content = null;
    }

}
