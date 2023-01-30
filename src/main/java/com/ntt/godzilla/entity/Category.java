package com.ntt.godzilla.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "description")
    private String description;
    @Column(name = "slug")
    private String slug;
    @Column(name = "status")
    private Integer status;
    @Column(name = "create_user")
    private String  create_user;
    @Column(name = "create_time")
    private Date create_time;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    private Date updateTime;
}
