package com.CityThrillsMorocco.Client.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String FirstName;
    private String LastName;
    @Column(unique = true)
    private String email;
    private String Phone;
    private String Password;
    private byte[] storedHash;
    private byte[] storedSalt;
}
