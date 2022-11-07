package com.example.demo.Mappers;

import com.example.demo.DTO.HospitalDto;
import com.example.demo.DTO.HouseDto;
import com.example.demo.DTO.PlotDto;
import com.example.demo.Entity.Hospital;
import com.example.demo.Entity.House;
import com.example.demo.Entity.Plot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

    HospitalDto hospitalToHospitalDto(Hospital hospital);

    Hospital hospitalDtoToHospital(HospitalDto hospitalDto);

    HouseDto hoseToHouseDto(House house);

    House houseDtoToHouse(HouseDto houseDto);

    PlotDto plotToPlotDto(Plot plot);

    Plot plotDtoToPlot(PlotDto plotDto);
}
