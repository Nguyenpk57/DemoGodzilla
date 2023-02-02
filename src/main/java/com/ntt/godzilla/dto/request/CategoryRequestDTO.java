package com.ntt.godzilla.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO extends BaseRequestDTO {
    @JsonProperty("categoryId")
    private Long categoryId;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("description")
    private String description;
}
