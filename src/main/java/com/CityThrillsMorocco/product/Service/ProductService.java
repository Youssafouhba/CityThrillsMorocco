package com.CityThrillsMorocco.product.Service;

import com.CityThrillsMorocco.product.Model.Product;
import com.CityThrillsMorocco.product.Repository.ProductRepository;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final UserService userService;
    public Product addProduct(Product product, Long id){
        var existingProduct = productRepository.selectExistsCode(product.getCode());
        if(existingProduct) throw new BadRequestException(" this product code " + product.getCode() + " already exists !!");
        UserDto client = userService.getUserById(id);
        product.setUser(mapper.map(client, User.class));
        productRepository.save(product);
        return product;
    }
    public void updateProduct(Long id,Product product)
            throws NoSuchAlgorithmException {
        var productt = findOrThrow(id);
        productt.setUser(product.getUser());
        productt.setCode(product.getCode());
        productt.setDescription(product.getDescription());
        productt.setName(product.getName());
        productRepository.save(productt);
    }
    public List<Product> getAllProducts(){
        return new ArrayList<Product>( productRepository.findAll());
    }
    public Product getProductById(Long id){
        var product = productRepository
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Product by id " + id + " was not found")
                );
        return product;
    }
    public void DeleteProductById(Long id){
        findOrThrow(id);
        productRepository.deleteById(id);
    }
    private Product findOrThrow(final Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("agence by id " + id + " was not found")
                );
    }
}
