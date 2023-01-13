package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.request.BaseRequestDTO;
import com.ntt.godzilla.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Value("${table.record_per_page:10}")
    private Integer recordPerPage;

    public PageRequest buildPageRequest(BaseRequestDTO baseRequestDTO) {
        if (baseRequestDTO == null) {
            return PaginationUtils.generatePageRequest(PaginationUtils.DEFAULT_CURRENT_PAGE, recordPerPage);
        }
        return PaginationUtils.generatePageRequest(baseRequestDTO.getPage(), baseRequestDTO.getOffset() == 0 ? recordPerPage : baseRequestDTO.getOffset());
    }

    public String getIpAddress() {
        HttpServletRequest req = getRequest();
        return req.getRemoteAddr();
    }

    public abstract HttpServletRequest getRequest();
}
