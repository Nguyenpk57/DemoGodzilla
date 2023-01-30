package com.ntt.godzilla.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO extends BaseRequestDTO {
    @JsonProperty("categoryIds")
    private List<Long> categoryIds;

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("productCode")
    private String productCode;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("discount")
    private Integer discount;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("createUser")
    private String createUser;

    @JsonProperty("updateUser")
    private String updateUser;

}
