package com.example.demo.Facade;

import com.example.demo.DTO.RecordDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Response.ResponseDto;
import com.example.demo.Service.Ipm.DoctorServiceImp;
import com.example.demo.Service.Ipm.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RecordFacade {

    private final UserServiceImp userServiceImp;

    private final DoctorServiceImp doctorServiceImp;


    @Autowired
    public RecordFacade(UserServiceImp userServiceImp, DoctorServiceImp doctorServiceImp) {
        this.userServiceImp = userServiceImp;
        this.doctorServiceImp = doctorServiceImp;
    }

    public ResponseEntity<?> saveRecord(RecordDto recordDto){
        if(recordDto == null){
            log.debug("RecordDto is null");
            throw new RuntimeException("Record save failed");
        }
        RecordDto recordDto1 = userServiceImp.saveRecord(recordDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(recordDto1);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> deleteRecord(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("Record not found");
        }
        userServiceImp.deleteRecord(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody("Record delete.Suggest");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> saveSchedule(ScheduleDto scheduleDto){
        if(scheduleDto == null){
            log.debug("Schedule is null");
            throw new RuntimeException("Save schedule failed");
        }
        ScheduleDto scheduleDto1 = doctorServiceImp.saveSchedule(scheduleDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(scheduleDto1);
        return ResponseEntity.ok(scheduleDto1);
    }

    public ResponseEntity<?> saveAllSchedule(List<ScheduleDto> scheduleDto){
        doctorServiceImp.saveAllSchedule(scheduleDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody("Suggest");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> deleteSchedule(Long id){
    if(id == null){
        log.debug("Id is null");
        throw new NotFoundException("Delete is failed");
    }
    doctorServiceImp.deleteSchedule(id);
    ResponseDto responseDto = new ResponseDto();
    responseDto.setBody("Suggest");
    return ResponseEntity.ok(responseDto);
    }
}
