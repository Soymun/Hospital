package com.example.demo.DTO;

import com.example.demo.Entity.Reception;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TroublesDto {

    private Long id;

    private Long userId;

    private Long doctorId;

    private String nameTroubles;

    private String about;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe")
    private Date date;

    private Reception reception;
}
