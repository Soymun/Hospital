package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
