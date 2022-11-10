package com.example.demo.Service.Ipm;

import com.example.demo.DTO.DoctorDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.DTO.TroublesDto;
import com.example.demo.Entity.*;
import com.example.demo.Mappers.DoctorMapper;
import com.example.demo.Repositories.DoctorRepository;
import com.example.demo.Repositories.ScheduleRepository;
import com.example.demo.Repositories.TroublesRepository;
import com.example.demo.Service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Slf4j
public class DoctorServiceImp implements DoctorService {

    @PersistenceContext
    EntityManager entityManager;
    private final DoctorRepository doctorRepository;

    private final TroublesRepository troublesRepository;

    private final ScheduleRepository scheduleRepository;

    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImp(DoctorRepository doctorRepository, TroublesRepository troublesRepository, ScheduleRepository scheduleRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.troublesRepository = troublesRepository;
        this.scheduleRepository = scheduleRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        log.info("Save doctor");
        Doctor doctor = doctorMapper.doctorDtoToDoctor(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        log.info("Doctor saved with id {}", savedDoctor.getId());
        return doctorMapper.doctorToDoctorDto(savedDoctor);
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        log.info("Update doctor");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Doctor> cu = cb.createCriteriaUpdate(Doctor.class);
        Root<Doctor> root = cu.getRoot();

        cu.set(Doctor_.ID, doctorDto.getId());
        cu.set(Doctor_.USER_ID, doctorDto.getUserId());
        cu.set(Doctor_.PLOT_ID, doctorDto.getPlotId());
        cu.set(Doctor_.QUALIFICATION, doctorDto.getQualification());

        cu.where(cb.equal(root.get(Doctor_.ID), doctorDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Doctor updated");
        return doctorDto;
    }

    @Override
    public void deleteDoctor(Long id) {
        log.info("Doctor delete with id {}", id);
        doctorRepository.deleteById(id);
        log.info("Doctor deleted");
    }

    @Override
    public ScheduleDto saveSchedule(ScheduleDto scheduleDto) {
        log.info("Save schedule");
        Schedule schedule = doctorMapper.scheduleDtoToSchedule(scheduleDto);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        log.info("Schedule saved with id {}", savedSchedule.getId());
        return doctorMapper.scheduleToScheduleDto(savedSchedule);
    }

    @Override
    public ScheduleDto updateScheduleDto(ScheduleDto scheduleDto) {
        log.info("Update schedule");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Schedule> cu = cb.createCriteriaUpdate(Schedule.class);
        Root<Schedule> root = cu.getRoot();

        cu.set(Schedule_.ID, scheduleDto.getId());
        cu.set(Schedule_.DATE, scheduleDto.getDate());
        cu.set(Schedule_.USER_ID, scheduleDto.getUserId());
        cu.set(Schedule_.DOCTOR_ID, scheduleDto.getDoctorId());

        cu.where(cb.equal(root.get(Schedule_.ID), scheduleDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Schedule updated");
        return scheduleDto;
    }

    @Override
    public void deleteSchedule(Long id) {
        log.info("Schedule delete with id {}", id);
        scheduleRepository.deleteById(id);
        log.info("Schedule deleted");
    }

    @Override
    public void saveAllSchedule(List<ScheduleDto> scheduleDtoList) {
        scheduleDtoList
                .stream()
                .peek(n -> log.info("Save schedule"))
                .map(doctorMapper::scheduleDtoToSchedule)
                .forEach(scheduleRepository::save);
        log.info("Schedules saves");
    }

    @Override
    public TroublesDto saveTroubles(TroublesDto troublesDto) {
        log.info("Save troubles");
        Troubles troubles = doctorMapper.troublesDtoToTroubles(troublesDto);
        Troubles savedTroubles = troublesRepository.save(troubles);
        log.info("Troubles saved with id {}", savedTroubles.getId());
        return doctorMapper.troublesToTroubleDto(savedTroubles);
    }

    @Override
    public TroublesDto updateTroubles(TroublesDto troublesDto) {
        log.info("Update troubles");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Troubles> cu = cb.createCriteriaUpdate(Troubles.class);
        Root<Troubles> root = cu.getRoot();

        cu.set(Troubles_.ID, troublesDto.getId());
        cu.set(Troubles_.USER_ID, troublesDto.getUserId());
        cu.set(Troubles_.DOCTOR_ID, troublesDto.getDoctorId());
        cu.set(Troubles_.NAME_TROUBLES, troublesDto.getNameTroubles());
        cu.set(Troubles_.ABOUT, troublesDto.getAbout());
        cu.set(Troubles_.DATE, troublesDto.getDate());
        cu.set(Troubles_.RECEPTION, troublesDto.getReception());

        cu.where(cb.equal(root.get(Doctor_.ID), troublesDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Troubles updated");
        return troublesDto;
    }

    @Override
    public void deleteTroubles(Long id) {
        log.info("Troubles delete with id {}", id);
        troublesRepository.deleteById(id);
        log.info("Troubles deleted");
    }

    @Override
    public List<TroublesDto> getAllTroublesByUserId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TroublesDto> cq = cb.createQuery(TroublesDto.class);
        Root<Troubles> root = cq.from(Troubles.class);

        cq.where(cb.equal(root.get(Troubles_.USER_ID), id));

        cq.multiselect(
                root.get(Troubles_.ID),
                root.get(Troubles_.USER_ID),
                root.get(Troubles_.DOCTOR_ID),
                root.get(Troubles_.NAME_TROUBLES),
                root.get(Troubles_.ABOUT),
                root.get(Troubles_.DATE),
                root.get(Troubles_.RECEPTION)
        );

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<ScheduleDto> getAllScheduleByUserId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleDto> cq = cb.createQuery(ScheduleDto.class);
        Root<Schedule> root = cq.from(Schedule.class);

        cq.where(cb.equal(root.get(Schedule_.USER_ID), id));

        cq.multiselect(
                root.get(Schedule_.ID),
                root.get(Schedule_.DATE),
                root.get(Schedule_.USER_ID),
                root.get(Schedule_.DOCTOR_ID)
        );

        return entityManager.createQuery(cq).getResultList();
    }
}
