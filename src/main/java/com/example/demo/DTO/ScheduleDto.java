package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe")
    private Date date;

    private Long userId;

    private Long doctorId;
}
