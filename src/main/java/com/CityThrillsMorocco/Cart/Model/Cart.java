package com.CityThrillsMorocco.Cart.Model;

import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.user.model.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Cart;
    private Double total_amount;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    @OneToMany(targetEntity = CartElement.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL ,mappedBy = "cart")
    @JsonManagedReference
    private List<CartElement> cartElements;
}
