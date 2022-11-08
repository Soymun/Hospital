package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class RecordDto {

    private Long id;

    private Date date;

    private Date dateRecord;

    private Long userId;

    private Long doctorId;

}
