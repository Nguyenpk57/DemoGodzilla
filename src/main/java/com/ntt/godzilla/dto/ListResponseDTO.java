package com.ntt.godzilla.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponseDTO<T> extends BaseResponseDTO {
    private List<T> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageDTO page;

    public ListResponseDTO() {
        super();
    }

    public ListResponseDTO(List<T> data) {
        super();
        this.data = data;
    }

    public ListResponseDTO(List<T> data, PageDTO page) {
        super();
        this.data = data;
        this.page = page;
    }

    public ListResponseDTO(String code, String message, List<T> data, PageDTO page) {
        super(code, message);
        this.data = data;
        this.page = page;
    }
}
