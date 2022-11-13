package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DayScheduleDto {

    private Long id;

    private LocalDate date;
}
