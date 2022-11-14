package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDto {

    private Long id;

    private Long userId;

    private Long plotId;

    private String qualification;
}
