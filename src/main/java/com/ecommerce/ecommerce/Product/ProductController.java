package com.ecommerce.ecommerce.Product;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addNewProduct(@RequestParam String name,
            @RequestParam BigDecimal price) {
        try {
            productService.addNewProduct(name, price);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product creation success.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product creation failed.");
        }
    }

    @GetMapping(path = "")
    public @ResponseBody Iterable<Product> getAllProducts() {
        try {
            return productService.getProducts();
        } catch (Exception e) {
            return null;
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could
            // not get Products");
        }
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody Iterable<Product> getProductByName(@PathVariable String name) {
        try {
            return productService.getProduct(name);
        } catch (Exception e) {
            return null;
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could
            // not get Product");
        }
    }

    @PutMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<String> updateProduct(@PathVariable String name,
            @RequestParam BigDecimal price) {
        boolean result = productService.updateProduct(name, price);
        if (result)
            return ResponseEntity.status(HttpStatus.OK).body("Product update Success");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not update Product");
    }

    @DeleteMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable String name) {
        try {
            boolean result = productService.deleteProductByName(name);
            if (result)
                return ResponseEntity.status(HttpStatus.OK).body("Delete Product success.");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product does not exist.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

}