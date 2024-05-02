package com.CityThrillsMorocco.comment.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int note;

    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne(targetEntity = Activity.class)
    @JoinColumn(nullable = false, name = "id_activity")
    private Activity activity;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Comment(){
        createdDate = new Date();
    }
}
