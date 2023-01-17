package com.ntt.godzilla.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordListResponse {
    @JsonProperty("page")
    private int currentPage;
    @JsonProperty("offset")
    private int pageSize;
    private long total;
    private List<?> records;
}
