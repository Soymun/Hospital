package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namePlot;

    @Column(name = "hospital_id")
    private Long hospitalId;

    @ManyToOne
    @JoinColumn(name = "hospital_id", insertable = false, updatable = false)
    private Hospital hospital;

    @OneToMany
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private List<House> houses;
}
