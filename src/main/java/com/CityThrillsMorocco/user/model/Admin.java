package com.CityThrillsMorocco.user.model;

import com.CityThrillsMorocco.agency.Model.Agence;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Admin extends User {

    // Admin-specific fields can be added here

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agence> agencies = new HashSet<>();

    // Standard constructors, getters, and setters
}
