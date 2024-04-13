package com.CityThrillsMorocco.Agence.Model;

import com.CityThrillsMorocco.Activity.Model.Activity;
import com.CityThrillsMorocco.Client.Model.Client;
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
    @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Client user;
    @OneToMany(mappedBy = "activity" , cascade = CascadeType.ALL)
    Set<Activity> activities = new HashSet<Activity>();
}
