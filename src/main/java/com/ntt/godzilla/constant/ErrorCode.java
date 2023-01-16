package com.ntt.godzilla.constant;

import com.ntt.godzilla.annotation.ErrorMessage;

public class ErrorCode {
    @ErrorMessage("Success")
    public static final String SUCCESS = "000000";
    @ErrorMessage("Existed Entity")
    public static final String EXISTED_ENTITY = "000001";
    @ErrorMessage("Invalid payload")
    public static final String INVALID_PAYLOAD = "000002";
    @ErrorMessage("Create fail")
    public static final String CREATE_FAIL = "000003";
    @ErrorMessage("Password is invalid")
    public static final String INVALID_PASSWORD = "000004";
    @ErrorMessage("Entity is not found")
    public static final String ENTITY_NOT_FOUND = "000005";
    @ErrorMessage("Your password doesn't match")
    public static final String PASSWORD_NOT_MATCH = "000006";
    @ErrorMessage("Update fail")
    public static final String UPDATE_FAIL = "000007";
    @ErrorMessage("Phone number is not valid")
    public static final String NOT_VALID_PHONE_NUM = "000008";
    @ErrorMessage("Email is not valid")
    public static final String NOT_VALID_EMAIL = "000009";
    public static final String UPLOAD_FAIL = "000011";
}
