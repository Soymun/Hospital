package com.example.demo.Controllers;


import com.example.demo.DTO.RecordDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.DTO.ScheduleListDto;
import com.example.demo.Facade.RecordFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class RecordController {

    private final RecordFacade recordFacade;

    @Autowired
    public RecordController(RecordFacade recordFacade) {
        this.recordFacade = recordFacade;
    }

    @PostMapping("/record")
    public ResponseEntity<?> saveRecord(@RequestBody RecordDto recordDto){
        return recordFacade.saveRecord(recordDto);
    }

    @DeleteMapping("/record/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id){
        return recordFacade.deleteRecord(id);
    }

    @PostMapping("/schedule")
    public ResponseEntity<?> saveSchedule(@RequestBody ScheduleDto scheduleDto){
        return recordFacade.saveSchedule(scheduleDto);
    }

    @PostMapping("/schedule/all")
    public ResponseEntity<?> saveAllSchedule(@RequestBody ScheduleListDto scheduleListDto){
        return recordFacade.saveAllSchedule(scheduleListDto.getScheduleDtoList());
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id){
        return recordFacade.deleteSchedule(id);
    }
}
