package com.ntt.godzilla.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO extends BaseRequestDTO{
    @JsonProperty("categoryId")
    private Long categoryId;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("createUser")
    private String createUser;
    @JsonProperty("createTime")
    private Date createTime;
    @JsonProperty("updateUser")
    private String updateUser;
    @JsonProperty("updateTime")
    private Date updateTime;
}
