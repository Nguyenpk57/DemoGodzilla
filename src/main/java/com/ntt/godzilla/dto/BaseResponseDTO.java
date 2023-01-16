package com.ntt.godzilla.dto;

import com.ntt.godzilla.constant.ErrorCode;

public class BaseResponseDTO {
    private String code;
    private String message;

    public BaseResponseDTO() {
        this.code = ErrorCode.SUCCESS;
        this.message = "";
    }
    public BaseResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
