package com.CityThrillsMorocco.product.Controller;

import com.CityThrillsMorocco.product.Model.Product;
import com.CityThrillsMorocco.product.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/agence")
@CrossOrigin("http://localhost:4200/")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/add")
    public Product saveproduct(@RequestBody Product product, Long id){
        return productService.addProduct(product,id);
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("id") Long id){
        productService.DeleteProductById(id);
    }

    @PutMapping("/update/{id}")
    public void putProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product
    ) throws NoSuchAlgorithmException {
        productService.updateProduct(id,product);
    }
}