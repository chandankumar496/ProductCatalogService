package com.chandan.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String sortName;

    private SortType sortType;
}
