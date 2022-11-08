package com.example.demo.Mappers;


import com.example.demo.DTO.DoctorDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.DTO.TroublesDto;
import com.example.demo.Entity.Doctor;
import com.example.demo.Entity.Schedule;
import com.example.demo.Entity.Troubles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor doctorDtoToDoctor(DoctorDto doctorDto);

    DoctorDto doctorToDoctorDto(Doctor doctor);

    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);

    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    Troubles troublesDtoToTroubles(TroublesDto troublesDto);

    TroublesDto troublesToTroubleDto(Troubles troubles);
}
