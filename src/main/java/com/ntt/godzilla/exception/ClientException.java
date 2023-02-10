package com.ntt.godzilla.exception;

import com.ntt.godzilla.factory.ResponseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientException extends RuntimeException {
    private static final long serialVersionUID = 7477382934189144413L;

    ResponseStatus status;
    HttpStatus httpStatus;
    Object data;

    public ClientException(ResponseStatus status, HttpStatus httpStatus) {
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public ClientException(ResponseStatus status, HttpStatus httpStatus, Object data) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public ClientException(ResponseStatus status) {
        this.status = status;
    }
}

