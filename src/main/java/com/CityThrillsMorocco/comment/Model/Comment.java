package com.CityThrillsMorocco.comment.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int note;

    private String content;
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @ManyToOne(targetEntity = Activity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_activity")
    private Activity activity;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
