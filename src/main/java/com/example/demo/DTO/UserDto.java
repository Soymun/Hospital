package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    public UserDto(Long id, String name, String surname, Long age, Long OMS, String snils, Long plotId, String namePlot) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.OMS = OMS;
        this.snils = snils;
        this.plotId = plotId;
        this.namePlot = namePlot;
    }

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Long age;

    private Long OMS;

    private String snils;

    private Long plotId;

    private String namePlot;
}
