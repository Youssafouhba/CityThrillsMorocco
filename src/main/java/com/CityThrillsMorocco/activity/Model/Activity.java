package com.CityThrillsMorocco.activity.Model;

import com.CityThrillsMorocco.Notification.Model.Notification;
import com.CityThrillsMorocco.Program.Model.Program;
import com.CityThrillsMorocco.Reservation.Model.Reservation;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.enumeration.City;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String designation;

    private String description;

    private String descriptiondetail;

    private Date startDate;

    private Date endDate;
    private String location;

    private Double price;

    private String category;

    private String imageUrl;

    private String status = "Active";

    private int maxParticipants;

    private int totalParticipants;
    private int participants;
    @JsonProperty("isAvailableYearRound")
    private boolean isAvailableYearRound;
    @JsonProperty("isFlexibleDates")

    private boolean isFlexibleDates;
    @JsonProperty("isPlacesLimited")
    private boolean isPlacesLimited;
    private Date bookingStartDate;

    private Date bookingEndDate;
    @Enumerated(EnumType.STRING)
    private City city;

    @ManyToOne(targetEntity = Agence.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "agence_id")
    @JsonIgnore
    private Agence agence;


    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @JsonBackReference
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Notification> notifications;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    @JsonBackReference
    private Program program;


    public void updateStatusWhenStartDateArrives() {
        Date currentDate = new Date();
        if (!isFlexibleDates) {
            if (currentDate.equals(startDate) || currentDate.after(startDate)) {
                if (endDate != null && currentDate.after(endDate)) {
                    this.status = "Expired";
                } else {
                    this.status = "Active";
                }
            }
        } else {
            if (bookingEndDate != null && currentDate.after(bookingEndDate)) {
                this.status = "Expired";
            }
        }
    }
}
