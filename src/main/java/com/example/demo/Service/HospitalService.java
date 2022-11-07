package com.example.demo.Service;

import com.example.demo.DTO.HospitalDto;
import com.example.demo.DTO.HouseDto;
import com.example.demo.DTO.PlotDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HospitalService {

    HospitalDto saveHospital(HospitalDto hospitalDto);

    HospitalDto updateHospital(HospitalDto hospitalDto);

    void deleteHospital(Long id);

    HospitalDto getHospitalById(Long id);

    HouseDto saveHouseInHospital(HouseDto houseDto);

    HouseDto updateHouse(HouseDto house);

    void deleteHouse(Long id);

    HouseDto getHouseById(Long id);

    PlotDto savePlot(PlotDto plotDto);

    PlotDto updatePlot(PlotDto plotDto);

    void deletePlot(Long id);

    PlotDto getPlotById(Long id);
}
