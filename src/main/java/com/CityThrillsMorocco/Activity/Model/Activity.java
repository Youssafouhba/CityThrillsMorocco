package com.CityThrillsMorocco.Activity.Model;

import com.CityThrillsMorocco.Agence.Model.Agence;
import com.CityThrillsMorocco.Enumeration.ActivityCategories;
import com.CityThrillsMorocco.Enumeration.Vielle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String designation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date duration;
    private String location;
    private String description;
    private Double tarif;
    @Enumerated(EnumType.STRING)
    private ActivityCategories category;
    @Enumerated(EnumType.STRING)
    private Vielle vielle;
    @OneToOne(targetEntity = Agence.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "agence_id")
    private Agence agence;
}
