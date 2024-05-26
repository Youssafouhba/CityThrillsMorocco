package com.CityThrillsMorocco.Program.Model;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int numberOfDays;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProgramElement> programElements = new ArrayList<>();

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Activity> activities = new ArrayList<>();

    // Méthode pour générer les ProgramElement lors de la création du Programme
    @PrePersist
    @PreUpdate
    public void generateProgramElements() {
        programElements.clear();
        for (int i = 0; i < numberOfDays; i++) {
            ProgramElement element = new ProgramElement();
            element.setName("Jour " + (i + 1));
            element.setProgram(this);
            programElements.add(element);
        }
    }
}
