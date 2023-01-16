package com.ntt.godzilla.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponseDTO<T> extends BaseResponseDTO {
    private T data;

    public SingleResponseDTO() {
        super();
    }

    public SingleResponseDTO(T data) {
        super();
        this.data = data;
    }

    public SingleResponseDTO(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }
}
