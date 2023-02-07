package com.ntt.godzilla.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String createUser;
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createTime;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    @UpdateTimestamp
    private Date updateTime;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "categories")
//    @JsonIgnore
//    private Set<Product> products = new HashSet<>();

}
