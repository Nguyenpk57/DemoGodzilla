package com.ntt.godzilla.dto.response;

public interface ResponseStatusCodeConstant {
    String SUCCESS_CODE = "R0000";
    String GENERAL_ERROR_CODE = "E0000";
    String MERCHANT_CLIENT_ERROR_CODE = "E0001";
    String INVALID_REQUEST = "E0002";
    String FIELD_MISSING_CODE = "E0003";
    String ERROR_NOT_FOUND_CODE = "E0004";
    String INVALID_DATA_TYPE_CODE = "E0005";
    String ERROR_ALREADY_IN_DATA = "E0006";
    String ID_NOT_NULL = "E0007";
    String DATA_IN_USE_CANNOT_BE_DELETE_OR_UPDATE = "E0008";
    String NO_DATA = "E0009";
}
