package com.ntt.godzilla.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GozBizException extends RuntimeException{
    private String code;

    public GozBizException(String code) {
        this.code = code;
    }
}
