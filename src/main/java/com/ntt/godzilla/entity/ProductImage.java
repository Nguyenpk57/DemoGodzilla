package com.ntt.godzilla.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dbo_product_image")
public class ProductImage {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    @Id
    private int id;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_by")
    private String createdUser;

    @Column(name = "create_date")
    private Date createdTime;

    @Column(name = "updated_by")
    private String updatedUser;

    @Column(name = "updated_date")
    private Date updatedTime;
}
