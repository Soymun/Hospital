package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {

    private Long id;

    private String nameHouse;

    private String idHouse;

    private Long plotId;
}
