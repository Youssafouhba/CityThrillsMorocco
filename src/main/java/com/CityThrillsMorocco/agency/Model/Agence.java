package com.CityThrillsMorocco.agency.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.User;
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
    @OneToMany(targetEntity = Activity.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Activity> activities = new HashSet<Activity>();
    @ManyToMany(mappedBy = "agences")
    private Set<User> users;
}
