package com.rest.api.demo.controller;

import com.rest.api.demo.business.dto.ProductModel;
import com.rest.api.demo.business.entity.Product;
import com.rest.api.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class ProductController {
@Autowired
private ProductService productService;
    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody ProductModel model) { //productModel->entity
        try {
            var prod = new Product(); //prod - Entity
            prod.setName(model.getName() );
            prod.setPrice(model.getPrice());

            Product p = this.productService.addProduct(prod);
            model.setId(p.getId());
            return new ResponseEntity<>(model, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Object> deleteProduct(@RequestParam String id) {
        try {
           Product product = this.productService.deleteProduct(id);
           ProductModel model = new ProductModel();
           model.setId(product.getId());
            model.setName(product.getName());
            model.setPrice(product.getPrice());
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatusCode.valueOf(500));
        }
    }


    @PutMapping("/updateProduct")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductModel model) {
        try {
            Product product = this.productService.getProduct(model.getId());
            product.setName(model.getName());
            product.setPrice(model.getPrice());
            Product p = this.productService.updateProduct(product);
            return new ResponseEntity<>(model, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductModel>> getProducts() {
        try {
            List<Product> list = new ArrayList<>();
            List<ProductModel> listModel = new ArrayList<>();
            list = this.productService.getProducts();
            for(Product p: list){
                ProductModel model = new ProductModel();
                model.setId(p.getId());
                model.setName(p.getName());
                model.setPrice(p.getPrice());
                listModel.add(model);
            }
            return new ResponseEntity<>(listModel, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }
    @GetMapping("/getProduct")
    public ResponseEntity<ProductModel> getProduct(@RequestParam String id) {
        try {
            ProductModel model =new ProductModel();
            Product p = this.productService.getProduct(id);
            model.setId(p.getId());
            model.setName(p.getName());
            model.setPrice(p.getPrice());

            return new ResponseEntity<>(model, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductModel> get(@PathVariable String id) {
        try {
            ProductModel model =new ProductModel();
            Product p = this.productService.getProduct(id);
            model.setId(p.getId());
            model.setName(p.getName());
            model.setPrice(p.getPrice());

            return new ResponseEntity<>(model, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }
}
