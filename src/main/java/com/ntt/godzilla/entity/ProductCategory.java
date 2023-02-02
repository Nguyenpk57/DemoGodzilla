package com.ntt.godzilla.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductCategoryId.class)
@Builder
public class ProductCategory {

    public static final Integer NEW = 1;
    public static final Integer DELETE = 9;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "status")
    private Integer status = 1;
}
