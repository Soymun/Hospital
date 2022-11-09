package com.example.demo.Controllers;


import com.example.demo.DTO.HospitalDto;
import com.example.demo.DTO.HouseDto;
import com.example.demo.DTO.PlotDto;
import com.example.demo.Entity.Hospital;
import com.example.demo.Service.Ipm.HospitalServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
@Slf4j
public class HospitalController {

    private final HospitalServiceImp hospitalServiceImp;

    public HospitalController(HospitalServiceImp hospitalServiceImp) {
        this.hospitalServiceImp = hospitalServiceImp;
    }

    @PostMapping("/save/hospital")
    public ResponseEntity<?> saveHospital(@RequestBody HospitalDto hospitalDto){
        log.info("Save hospital");

        HospitalDto hospitalDto1 = hospitalServiceImp.saveHospital(hospitalDto);

        log.info("Save end");
        return ResponseEntity.ok(hospitalDto1);
    }

    @PutMapping("/update/hospital")
    public ResponseEntity<?> updateHospital(@RequestBody HospitalDto hospitalDto){
        log.info("Update start");

        HospitalDto hospitalDto1 = hospitalServiceImp.updateHospital(hospitalDto);

        log.info("Update end");

        return ResponseEntity.ok(hospitalDto1);
    }

    @DeleteMapping("/delete/hospital/{id}")
    public ResponseEntity<?> deleteHospital(@PathVariable Long id){
        log.info("Delete hospital");

        hospitalServiceImp.deleteHospital(id);

        log.info("Hospital deleted");

        return ResponseEntity.ok("Suggest");
    }

    @PostMapping("/save/plot")
    public ResponseEntity<?> savePlot(@RequestBody PlotDto plotDto){
        log.info("Save plot");

        PlotDto plotDto1 = hospitalServiceImp.savePlot(plotDto);

        log.info("Save plot end");

        return ResponseEntity.ok(plotDto1);
    }

    @PutMapping("/update/plot")
    public ResponseEntity<?> updatePlot(@RequestBody PlotDto plotDto){
        log.info("Update plot");

        PlotDto plotDto1 = hospitalServiceImp.updatePlot(plotDto);

        log.info("Update plot end");

        return ResponseEntity.ok(plotDto1);
    }

    @DeleteMapping("/delete/plot/{id}")
    public ResponseEntity<?> deletePlot(@PathVariable Long id){
        log.info("Delete plot");

        hospitalServiceImp.deletePlot(id);

        log.info("Delete plot end");

        return ResponseEntity.ok("Suggest");
    }

    @PostMapping("/save/home")
    public ResponseEntity<?> saveHome(@RequestBody HouseDto houseDto){
        log.info("Save house");

        HouseDto houseDto1 = hospitalServiceImp.saveHouseInHospital(houseDto);

        log.info("Save house end");

        return ResponseEntity.ok(houseDto1);
    }

    @PutMapping("/update/house")
    public ResponseEntity<?> updateHouse(@RequestBody HouseDto houseDto){
        log.info("Update house");

        HouseDto houseDto1 = hospitalServiceImp.updateHouse(houseDto);

        log.info("Update house end");

        return ResponseEntity.ok(houseDto1);
    }

    @DeleteMapping("/delete/house/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable Long id){
        log.info("Delete house");

        hospitalServiceImp.deleteHouse(id);

        log.info("Delete house end");

        return ResponseEntity.ok("Suggest");
    }
}
