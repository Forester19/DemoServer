package com.realserver.controller;

import com.realserver.exceptions.NotFoundException;
import com.realserver.model.Product;
import com.realserver.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    private ArrayList<Product> listProducts = new ArrayList<>();

    public ProductController() {
    }

    @GetMapping
    public List<Product> getProducts() {
        System.out.println("get products ");
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        return getProductById(id);
    }

    @PostMapping
    public List<Product> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return (List<Product>) productRepository.findAll();
    }

    @PutMapping("{id}")
    public List<Product> updateProduct(@RequestBody Product product, @PathVariable int id) {
        Product selectedProduct = getProductById(id);
        product.setId(createId());
        listProducts.set(listProducts.indexOf(selectedProduct), product);
        return listProducts;
    }

    @DeleteMapping("{id}")
    public List<Product> deleteProduct(@PathVariable int id) {
        listProducts.removeIf(P -> P.getId() == id);
        return listProducts;
    }

    private int createId() {
        int id = listProducts.size();
        return ++id;
    }

    private Product getProductById(int id) {
        return listProducts.stream().filter(useR -> useR.getId() == id).findFirst().orElseThrow(NotFoundException::new);
    }

}
