package com.ntt.godzilla.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryId implements Serializable {

    private Long productId;

    private Long categoryId;
}
