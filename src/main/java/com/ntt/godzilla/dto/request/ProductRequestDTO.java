package com.ntt.godzilla.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String productCode;
    private String productName;
    private String description;
    private Double price;
    private Integer discount;
}
