package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameHouse;

    private String idHouse;

    @Column(name = "plot_id")
    private Long plotId;

    @ManyToOne
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private Plot plot;
}
