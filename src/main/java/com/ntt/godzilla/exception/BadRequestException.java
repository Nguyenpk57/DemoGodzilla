package com.ntt.godzilla.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private String code;

    public BadRequestException(String code) {
        this.code = code;
    }
}
