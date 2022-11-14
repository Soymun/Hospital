package com.example.demo.Facade;


import com.example.demo.DTO.DoctorDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.DTO.TroublesDto;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Response.ResponseDto;
import com.example.demo.Service.Ipm.DoctorServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class DoctorFacade{

    private final DoctorServiceImp doctorServiceImp;

    @Autowired
    public DoctorFacade(DoctorServiceImp doctorServiceImp) {
        this.doctorServiceImp = doctorServiceImp;
    }

    public ResponseEntity<?> saveDoctor(DoctorDto doctorDto){
        if(doctorDto == null){
            log.debug("Doctor is null");
            throw new RuntimeException("Save doctor failed");
        }
        DoctorDto doctorDto1 = doctorServiceImp.saveDoctor(doctorDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(doctorDto1);
        return ResponseEntity.ok(responseDto);
    }


    public ResponseEntity<?> getDoctor(Long id){
        if(id == null){
            log.debug("Id doctor is null");
            throw new NotFoundException("Doctor not found");
        }
        DoctorDto doctorDto = doctorServiceImp.getDoctorById(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(doctorDto);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?>  getAllDoctorsByPlotId(Long id){
        if( id == null){
            log.debug("Id place is null");
            throw new NotFoundException("Doctors not founds");
        }
        List<DoctorDto> doctorDtos = doctorServiceImp.getDoctorsByPlotId(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(doctorDtos);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?>  saveTroubles(TroublesDto troublesDto){
        if(troublesDto == null){
            log.debug("Troubles is null");
            throw new RuntimeException("Save troubles failed");
        }
        TroublesDto troublesDto1 = doctorServiceImp.saveTroubles(troublesDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(troublesDto1);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?>  updateTroubles(TroublesDto troublesDto){
        if(troublesDto == null){
            log.debug("Troubles is null");
            throw new NotFoundException("Troubles not found");
        }
        TroublesDto updatedTroubles = doctorServiceImp.updateTroubles(troublesDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(updatedTroubles);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?>  getOneTroubles(Long id){
        if(id == null){
            log.debug("Id Troubles is null");
            throw new NotFoundException("Troubles not found");
        }
        TroublesDto troublesDto = doctorServiceImp.getTroubles(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(troublesDto);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?>  getAllTroubleByUserId(Long id){
        if(id == null){
            log.debug("User id is null");
            throw new NotFoundException("Troubles not found");
        }
        List<TroublesDto> troublesDtoList = doctorServiceImp.getTroublesByUserId(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(troublesDtoList);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?>  getScheduleByDoctorIdAndDay(Long id, Date date){
        if(id == null || date == null){
            log.debug("Id or date is null");
            throw new NotFoundException("Schedules not founds");
        }
        List<ScheduleDto> scheduleDtoList = doctorServiceImp.getScheduleByDoctorIdAndDay(id, date);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(scheduleDtoList);
        return ResponseEntity.ok(responseDto);
    }
}
