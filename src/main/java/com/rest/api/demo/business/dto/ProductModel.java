package com.rest.api.demo.business.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter

public class ProductModel {
    private String id;
    private String name;
    private float price;
}

