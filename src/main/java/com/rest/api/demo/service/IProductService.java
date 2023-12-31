package com.rest.api.demo.service;

import com.rest.api.demo.business.entity.Product;

import java.util.List;

public interface IProductService  {

    List<Product> findByCatgory();

}
