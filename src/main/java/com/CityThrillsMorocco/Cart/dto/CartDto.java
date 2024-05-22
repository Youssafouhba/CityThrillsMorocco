package com.CityThrillsMorocco.Cart.dto;

import com.CityThrillsMorocco.cart_element.dto.CartElementDto;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.user.model.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long id;
    private Double total_amount;
    private List<CartElementDto> cartElements;
}
