package com.CityThrillsMorocco.order.model;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.orderElement.model.OrderElement;
import com.CityThrillsMorocco.payment.model.Payment;
import com.CityThrillsMorocco.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(targetEntity = OrderElement.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "order")
    private List<OrderElement> orderItems;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

}
