package com.CityThrillsMorocco.activity.Model;

import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.enumeration.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
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

    private String description;

    private String descriptiondetail;

    @Temporal(TemporalType.TIMESTAMP)
    private Date starteddate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finisheddate;

    private LocalTime finishedduration;

    private LocalTime startedduration;

    private String location;

    private Double price;

    private String category;

    private String imageUrl;

    private String status = "Active";

    @Enumerated(EnumType.STRING)
    private City city;

    @ManyToOne(targetEntity = Agence.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "agence_id")
    private Agence agence;

}
