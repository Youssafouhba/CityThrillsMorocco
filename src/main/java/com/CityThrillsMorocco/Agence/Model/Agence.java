package com.CityThrillsMorocco.Agence.Model;

import com.CityThrillsMorocco.Activity.Model.Activity;
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
    private String Location;
    @Column(unique = true)
    private String email;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @OneToMany(targetEntity = Activity.class, fetch = FetchType.EAGER)
    Set<Activity> activities = new HashSet<Activity>();
}
