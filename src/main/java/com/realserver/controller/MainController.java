package com.realserver.controller;

import com.realserver.exceptions.NotFoundException;
import com.realserver.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("products")
public class MainController {
    public List<Product> listProducts = new ArrayList<Product>() {{
        add(new Product(1, "fewfw1", "wefwf", 100));
        add(new Product(2, "uswefwefer2", "pawefwefssword1", 200));
        add(new Product(3, "uswefwefwer3", "passwwefweford1", 300));
        add(new Product(4, "uswewefer4", "passwefwfword1", 400));
        add(new Product(5, "uswefwer5", "passwweford1", 500));
    }};

    @GetMapping
    public List<Product> getProducts() {
        return listProducts;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        return getProductById(id);
    }

    @PostMapping
    public List<Product> addProduct(@RequestBody Product product) {
        product.setId(createId());
        listProducts.add(product);
        return listProducts;
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
