package com.ntt.godzilla.exception;

import com.ntt.godzilla.factory.IResponseStatus;
import com.ntt.godzilla.factory.ResponseStatus;

public class BadRequestValidationWithMessageFormattedException extends ValidationException {
    private static final long serialVersionUID = 7801285748375533554L;

    public BadRequestValidationWithMessageFormattedException(IResponseStatus errorStatus) {
        super(errorStatus);
    }

    public BadRequestValidationWithMessageFormattedException(IResponseStatus errorStatus, String message) {
        super(errorStatus, message);
    }

    public BadRequestValidationWithMessageFormattedException(String code, String message) {
        super(message);
        errorStatus = new ResponseStatus(code, message);
    }

    public BadRequestValidationWithMessageFormattedException(String code, String message, String detailMessage) {
        super(detailMessage);
        errorStatus = new ResponseStatus(code, message);
    }

    @Override
    public IResponseStatus getErrorStatus() {
        return super.getErrorStatus();
    }
}
