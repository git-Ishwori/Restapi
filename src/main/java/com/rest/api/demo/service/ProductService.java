package com.rest.api.demo.service;

import com.rest.api.demo.business.entity.Product;
import com.rest.api.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService  {
    @Autowired
    private IcmpApi icmpApi;
    @Autowired
    private ProductRepository productRepository;
    public Product addProduct(Product product)
    {
       return  this.productRepository.insert(product);
    }

    public void callApi(int x)
    {
        this.icmpApi.callApi();
    }
    public Product getProduct(String id)
    {
        Optional<Product> prod =  this.productRepository.findById(id);
        return prod.get();
    }
    public List<Product> getProducts()
    {
        return this.productRepository.findAll();
    }
    public Product updateProduct(Product product)
    {
        return  this.productRepository.save(product);
    }

    public Product deleteProduct(String  id)
    {
        Optional<Product> product =  this.productRepository.findById(id);
        this.productRepository.deleteById(id);
        return product.get();
    }

    @Override
    public List<Product> findByCatgory(){
        long k = this.getProducts().stream()
                .filter(p -> p.getName().equals("f"))
                .count();
        return  this.getProducts();
    }
}
