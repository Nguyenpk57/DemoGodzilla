package com.ntt.godzilla.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
    public static final int DEFAULT_RECORD_PER_PAGE = 10;
    public static final int DEFAULT_CURRENT_PAGE = 0;
    public static final Sort.Direction DEFAULT_SORT_DIRECTION;
    public static final String DEFAULT_SORT_PROPERTY = "id";
    public static final String ASC = "asc";

    private PaginationUtils() {
    }

    public static PageRequest generatePageRequest(final int currentPage, final int pageSize) {
        return PageRequest.of(currentPage, pageSize);
    }


    static {
        DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;
    }
}
