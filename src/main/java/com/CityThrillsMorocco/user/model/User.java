package com.CityThrillsMorocco.user.model;

import com.CityThrillsMorocco.Notification.Model.Notification;
import com.CityThrillsMorocco.Reservation.Model.Reservation;
import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
  private String provider;
  @ManyToMany
  @JsonIgnore
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(
                  name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "role_id", referencedColumnName = "id"))
  @JsonBackReference
  private Collection<Role> roles;

  @ManyToMany
  @JsonIgnore
  @JoinTable(
          name = "user_agence",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "agence_id"))
  @JsonBackReference
  private Set<Agence> agences;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Reservation> reservations = new ArrayList<>();


  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Notification> notifications;
}
