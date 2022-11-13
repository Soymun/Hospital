package com.example.demo.DTO;

import lombok.Data;

@Data
public class RegistrationDto {

    private String name;

    private String surname;

    private String email;

    private String password;

    private Long age;

    private Long oms;

    private String snils;

    private Long plotId;
}
