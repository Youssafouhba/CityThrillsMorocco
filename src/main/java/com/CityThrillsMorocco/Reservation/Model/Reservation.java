package com.CityThrillsMorocco.Reservation.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    @JsonBackReference("activity")
    @JsonIgnore
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("reservation")
    @JsonIgnore
    private User user;

    private int participantCount;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

}
