package com.ntt.godzilla.factory;

import com.ntt.godzilla.dto.response.ResponseStatusCodeConstant;

public enum ResponseStatusEnum implements IResponseStatus {

    SUCCESS(ResponseStatusCodeConstant.SUCCESS_CODE, "Success"),
    GENERAL_ERROR(ResponseStatusCodeConstant.GENERAL_ERROR_CODE, "Any error occur"),
    INVALID_CREDENTIALS(ResponseStatusCodeConstant.MERCHANT_CLIENT_ERROR_CODE, "Unauthorized"),
    INVALID_INPUT(ResponseStatusCodeConstant.INVALID_REQUEST, "Input value is invalid"),
    FIELD_MISSING(ResponseStatusCodeConstant.FIELD_MISSING_CODE, "Field missing code"),
    BODY_MISSING(ResponseStatusCodeConstant.FIELD_MISSING_CODE, "Request body is missing code"),
    INVALID_DATA_TYPE(ResponseStatusCodeConstant.INVALID_DATA_TYPE_CODE, "Field %s is in invalid data type"),
    ERROR_NOT_FOUND(ResponseStatusCodeConstant.ERROR_NOT_FOUND_CODE, "Error not found"),
    ERROR_ALREADY_IN_DATA(ResponseStatusCodeConstant.ERROR_ALREADY_IN_DATA, "already in the data"),
    ID_NOT_NULL(ResponseStatusCodeConstant.ID_NOT_NULL, "Id not null"),
    NO_DATA(ResponseStatusCodeConstant.NO_DATA, "NO_DATA"),
    DATA_IN_USE_CANNOT_BE_DELETE_OR_UPDATE(ResponseStatusCodeConstant.DATA_IN_USE_CANNOT_BE_DELETE_OR_UPDATE, "Data in use cannot be delete or update");;
    private String code;
    private String message;

    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseStatusEnum fromCode(String code) {
        for (ResponseStatusEnum s : ResponseStatusEnum.values()) {
            if (s.getCode().equalsIgnoreCase(code)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
