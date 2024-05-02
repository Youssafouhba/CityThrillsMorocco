package com.CityThrillsMorocco.Cart.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
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
    private Long number_persons;
    private Double sub_total;
    private Double total_amount;
    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "cart_activity",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> activityList;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;



}
