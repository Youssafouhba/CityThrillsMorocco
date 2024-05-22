package com.CityThrillsMorocco.payment.model;

import com.CityThrillsMorocco.order.model.Order;
import com.CityThrillsMorocco.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String firstName;

    private String lastName;
    private String email;

    @Column(name = "creation_date")
    private Date creationDate;
    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
