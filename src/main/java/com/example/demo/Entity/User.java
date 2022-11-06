package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private Long age;

    private Role role;

    private Long OMS;

    private String snils;

    @Column(name = "plot_id")
    private Long plotId;

    @ManyToOne
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private Plot plot;

}
