package com.example.demo.Service;

import com.example.demo.DTO.DoctorDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.DTO.TroublesDto;
import com.example.demo.Entity.Schedule;

import java.util.List;

public interface DoctorService {

    DoctorDto saveDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(DoctorDto doctorDto);

    void deleteDoctor(Long id);

    ScheduleDto saveSchedule(ScheduleDto scheduleDto);

    ScheduleDto updateScheduleDto(ScheduleDto scheduleDto);

    void deleteSchedule(Long id);

    void saveAllSchedule(List<ScheduleDto> scheduleDtoList);

    TroublesDto saveTroubles(TroublesDto troublesDto);

    TroublesDto updateTroubles(TroublesDto troublesDto);

    void deleteTroubles(Long id);

}
