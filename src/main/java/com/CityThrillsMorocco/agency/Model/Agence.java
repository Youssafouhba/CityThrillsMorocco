package com.CityThrillsMorocco.agency.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.Admin;
import com.CityThrillsMorocco.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    @Column(unique = true)
    private String email;
    private String phone;
    @OneToMany(targetEntity = Activity.class, fetch = FetchType.EAGER)
    // No @JsonBackReference here if it's unrelated
    @JsonBackReference
    Set<Activity> activities = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
