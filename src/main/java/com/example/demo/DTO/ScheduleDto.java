package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class ScheduleDto {

    private Long id;

    private Date date;

    private Long userId;

    private Long doctorId;
}
