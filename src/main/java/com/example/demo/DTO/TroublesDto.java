package com.example.demo.DTO;

import com.example.demo.Entity.Doctor;
import com.example.demo.Entity.Reception;
import com.example.demo.Entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class TroublesDto {

    private Long id;

    private Long userId;

    private Long doctorId;

    private String nameTroubles;

    private String about;

    private Date date;

    private Reception reception;
}
