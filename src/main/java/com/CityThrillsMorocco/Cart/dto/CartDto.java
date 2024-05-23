package com.CityThrillsMorocco.Cart.dto;

import com.CityThrillsMorocco.cart_element.dto.CartElementDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long id;
    private Double total_amount;
    private List<CartElementDto> cartElements;
}
