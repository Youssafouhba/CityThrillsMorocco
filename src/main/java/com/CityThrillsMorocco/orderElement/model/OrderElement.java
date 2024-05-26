package com.CityThrillsMorocco.orderElement.model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.order.model.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_elements")
public class OrderElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nbrOfPerson;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;
    private Double sub_total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // Ajout d'une colonne pour la clé étrangère
    @JsonBackReference // Ajout pour éviter la récursion dans les JSON
    private Order order;
}
