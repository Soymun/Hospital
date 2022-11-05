package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "plot_id")
    private Long plotId;

    @ManyToOne
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private Plot plot;

    private String qualification;
}
