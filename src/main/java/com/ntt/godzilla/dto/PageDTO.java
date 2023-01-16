package com.ntt.godzilla.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ntt.godzilla.constant.ParamKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    @JsonProperty(ParamKey.PAGE)
    private int page;
    @JsonProperty(ParamKey.SIZE)
    private int size;
    @JsonProperty(ParamKey.TOTAL_ELEMENT)
    private long totalElement;
    @JsonProperty(ParamKey.TOTAL_PAGE)
    private long totalPage;
    @JsonProperty(ParamKey.IS_LAST)
    private boolean isLast;
    @JsonProperty(ParamKey.IS_FIRST)
    private boolean isFirst;

    public static PageDTO getPage(Page page) {
        if (page == null) {
            return null;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setFirst(page.isFirst());
        pageDTO.setLast(page.isLast());
        pageDTO.setPage(page.getNumber() + 1);
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalElement(page.getTotalElements());
        pageDTO.setTotalPage(page.getTotalPages());
        return pageDTO;
    }

}

