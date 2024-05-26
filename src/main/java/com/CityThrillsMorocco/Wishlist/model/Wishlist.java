package com.CityThrillsMorocco.Wishlist.model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    @JsonBackReference
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wishlist_activities",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    @JsonManagedReference
    private List<Activity> activities;


    public List<Activity> getActivities() {
        if (this.activities == null) {
            this.activities = new ArrayList<>();
        }
        return this.activities;
    }



}
