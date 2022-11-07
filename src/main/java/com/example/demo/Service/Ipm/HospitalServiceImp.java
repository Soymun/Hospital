package com.example.demo.Service.Ipm;

import com.example.demo.DTO.HospitalDto;
import com.example.demo.DTO.HouseDto;
import com.example.demo.DTO.PlotDto;
import com.example.demo.Entity.*;
import com.example.demo.Mappers.HospitalMapper;
import com.example.demo.Repositories.HospitalRepository;
import com.example.demo.Repositories.HouseRepository;
import com.example.demo.Repositories.PlotRepository;
import com.example.demo.Service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Service
@Slf4j
public class HospitalServiceImp implements HospitalService {

    private final HospitalMapper hospitalMapper;

    private final HospitalRepository hospitalRepository;

    private final PlotRepository plotRepository;

    private final HouseRepository houseRepository;

    @Autowired
    public HospitalServiceImp(HospitalMapper hospitalMapper, HospitalRepository hospitalRepository, PlotRepository plotRepository, HouseRepository houseRepository) {
        this.hospitalMapper = hospitalMapper;
        this.hospitalRepository = hospitalRepository;
        this.plotRepository = plotRepository;
        this.houseRepository = houseRepository;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public HospitalDto saveHospital(HospitalDto hospitalDto) {
        log.info("Save hospital");
        Hospital saveHospital = hospitalMapper.hospitalDtoToHospital(hospitalDto);
        Hospital savedHospital = hospitalRepository.save(saveHospital);
        log.info("Saved hospital have id {}", savedHospital.getId());
        return hospitalMapper.hospitalToHospitalDto(savedHospital);
    }

    @Override
    public HospitalDto updateHospital(HospitalDto hospitalDto) {
        log.info("Hospital updating");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Hospital> cu = cb.createCriteriaUpdate(Hospital.class);
        Root<Hospital> root = cu.getRoot();

        cu.set(Hospital_.ID, hospitalDto.getId());
        cu.set(Hospital_.NAME_ORGANIZATION, hospitalDto.getNameOrganization());

        cu.where(cb.equal(root.get(Hospital_.ID), hospitalDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Hospital updated name organization {}", hospitalDto.getNameOrganization());
        return hospitalDto;
    }

    @Override
    public void deleteHospital(Long id) {
        log.info("Delete hospital with id {}", id);
        hospitalRepository.deleteById(id);
        log.info("Hospital delete");
    }

    @Override
    public HospitalDto getHospitalById(Long id) {
        log.info("Get hospital with id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HospitalDto> cq = cb.createQuery(HospitalDto.class);
        Root<Hospital> root = cq.from(Hospital.class);

        cq.where(cb.equal(root.get(Hospital_.ID), id));
        cq.multiselect(
                root.get(Hospital_.ID),
                root.get(Hospital_.NAME_ORGANIZATION)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public HouseDto saveHouseInHospital(HouseDto houseDto) {
        log.info("Save house");
        House house = hospitalMapper.houseDtoToHouse(houseDto);
        House savedHouse = houseRepository.save(house);
        log.info("Saved house with id {}", savedHouse.getId());
        return hospitalMapper.hoseToHouseDto(savedHouse);
    }

    @Override
    public HouseDto updateHouse(HouseDto house) {
        log.info("House updating");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<House> cu = cb.createCriteriaUpdate(House.class);
        Root<House> root = cu.getRoot();

        cu.set(House_.ID, house.getId());
        cu.set(House_.PLOT_ID, house.getPlotId());
        cu.set(House_.ID_HOUSE, house.getIdHouse());
        cu.set(House_.NAME_HOUSE, house.getNameHouse());

        cu.where(cb.equal(root.get(House_.ID), house.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("House updated id {}", house.getIdHouse());
        return house;
    }

    @Override
    public void deleteHouse(Long id) {
        log.info("Delete house with id {}", id);
        houseRepository.deleteById(id);
        log.info("House delete");
    }

    @Override
    public HouseDto getHouseById(Long id) {
        log.info("Get house with id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HouseDto> cq = cb.createQuery(HouseDto.class);
        Root<House> root = cq.from(House.class);

        cq.where(cb.equal(root.get(House_.ID), id));
        cq.multiselect(
                root.get(House_.ID),
                root.get(House_.PLOT_ID),
                root.get(House_.ID_HOUSE),
                root.get(House_.NAME_HOUSE)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public PlotDto savePlot(PlotDto plotDto) {
        log.info("Save plot");
        Plot plot = hospitalMapper.plotDtoToPlot(plotDto);
        Plot savedPlot = plotRepository.save(plot);
        log.info("Saved plot with id {}", savedPlot.getId());
        return hospitalMapper.plotToPlotDto(savedPlot);
    }

    @Override
    public PlotDto updatePlot(PlotDto plotDto) {
        log.info("Plot updating");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Plot> cu = cb.createCriteriaUpdate(Plot.class);
        Root<Plot> root = cu.getRoot();

        cu.set(Plot_.ID, plotDto.getId());
        cu.set(Plot_.NAME_PLOT, plotDto.getNamePlot());
        cu.set(Plot_.HOSPITAL_ID, plotDto.getHospitalId());

        cu.where(cb.equal(root.get(User_.ID), plotDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Plot updated id {}", plotDto.getId());
        return plotDto;
    }

    @Override
    public void deletePlot(Long id) {
        log.info("Delete plot with id {}", id);
        plotRepository.deleteById(id);
        log.info("Plot deleted");
    }

    @Override
    public PlotDto getPlotById(Long id) {
        log.info("Get plot with id{}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PlotDto> cq = cb.createQuery(PlotDto.class);
        Root<Plot> root = cq.from(Plot.class);

        cq.where(cb.equal(root.get(Plot_.ID), id));

        cq.multiselect(
                root.get(Plot_.ID),
                root.get(Plot_.NAME_PLOT),
                root.get(Plot_.HOSPITAL_ID)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }
}
