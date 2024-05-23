package com.CityThrillsMorocco.Program.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProgramElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String day;
    private String description;



    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    @JsonBackReference
    private Program program;

}
