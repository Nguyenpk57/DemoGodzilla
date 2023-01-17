package com.ntt.godzilla.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO extends BaseRequestDTO {
    private Long productId;
    private String productCode;

    @JsonProperty("productName")
    private String productName;

    private String description;
    private BigDecimal price;
    private Integer discount;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

}
