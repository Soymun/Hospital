package com.example.demo.Controllers;


import com.example.demo.DTO.DayScheduleDto;
import com.example.demo.DTO.DoctorDto;
import com.example.demo.DTO.TroublesDto;
import com.example.demo.Facade.DoctorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class DoctorController {

    private final DoctorFacade doctorFacade;

    @Autowired
    public DoctorController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> saveDoctor(@RequestBody DoctorDto doctorDto){
        return doctorFacade.saveDoctor(doctorDto);
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id){
        return doctorFacade.getDoctor(id);
    }

    @GetMapping("/doctor/plot/{id}")
    public ResponseEntity<?> getDoctorsByPlotId(@PathVariable Long id){
        return doctorFacade.getAllDoctorsByPlotId(id);
    }

    @PostMapping("troubles")
    public ResponseEntity<?> saveTroubles(@RequestBody TroublesDto troublesDto){
        return doctorFacade.saveTroubles(troublesDto);
    }

    @PutMapping("troubles")
    public ResponseEntity<?> updateTroubles(@RequestBody TroublesDto troublesDto){
        return doctorFacade.updateTroubles(troublesDto);
    }

    @GetMapping("/troubles/{id}")
    public ResponseEntity<?> getTroubles(@PathVariable Long id){
        return doctorFacade.getOneTroubles(id);
    }

    @GetMapping("/schedule")
    public ResponseEntity<?> getSchedule(@RequestBody DayScheduleDto dayScheduleDto){
        return doctorFacade.getScheduleByDoctorIdAndDay(dayScheduleDto.getId(), dayScheduleDto.getDate());
    }
}
