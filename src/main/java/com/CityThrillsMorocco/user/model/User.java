package com.CityThrillsMorocco.user.model;

import com.CityThrillsMorocco.Notification.Model.Notification;
import com.CityThrillsMorocco.Reservation.Model.Reservation;
import com.CityThrillsMorocco.Wishlist.model.Wishlist;
import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;
  private String firstname;
  private String lastname;
  @Column(unique = true)
  private String email;
  private String phone;
  private String password;
  private boolean isEnabled;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Wishlist wishlist;
  private String provider;
  @ManyToMany
  @JsonIgnore
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(
                  name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "role_id", referencedColumnName = "id"))
  private Collection<Role> roles;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  @JsonManagedReference("reservation")
  private List<Reservation> reservations = new ArrayList<>();



  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonManagedReference("notification")
  private List<Notification> notifications;
}
