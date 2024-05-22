package com.CityThrillsMorocco.user.model;

import com.CityThrillsMorocco.Wishlist.model.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
  private byte[] storedHash;
  private byte[] storedSalt;
  private boolean isEnabled;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Wishlist wishlist;
}
