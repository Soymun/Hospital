package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "hospital_id", insertable = false, updatable = false)
    private List<Plot> plots;

    private String nameOrganization;
}
