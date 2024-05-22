package com.CityThrillsMorocco.agence.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private byte[] storedHash;
    private byte[] storedSalt;
    private boolean isEnabled;
    @OneToMany(targetEntity = Activity.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Activity> activities = new HashSet<Activity>();
}
