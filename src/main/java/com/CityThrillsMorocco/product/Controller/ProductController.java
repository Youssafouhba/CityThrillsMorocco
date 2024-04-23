package com.CityThrillsMorocco.product.Controller;

import com.CityThrillsMorocco.product.Model.Product;
import com.CityThrillsMorocco.product.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/Admin/products")
@CrossOrigin("http://localhost:4200/")
public class ProductController {
    private final ProductService productService;

    @GetMapping("all")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public Product saveproduct(@RequestBody Product product){
        return productService.addProduct(product);
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


}